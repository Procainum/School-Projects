# Who:  Sebastian Vivanco
# What: encrypt.asm
# Why:  A utility to encrypt a text document via a passphrase
# When: Created 22 Nov, Due 6 Dec
# How:
#       $s5: Bytes read
#       $s6: Source/input File descriptor
#       $s7: Output file descriptor
# SELF NOTE: Must run QtSpim as admin on windows to allow file creation, apparently

.data
NamePrompt:         .asciiz     "Enter the full path to the document you wish to encrypt or decrypt: "
FileNameBuffer:     .space      500
FileBuffer:         .space      1024

OutPrompt:          .asciiz     "Enter the full path to where you'd like your output to go: "
OutputBuffer:       .space      500

PassPrompt:         .asciiz     "Please enter your passphrase: "
PassBuffer:         .space      512

welcome:            .asciiz     "Welcome to this encryption utility\n\n"
doneMsg:            .asciiz     "\nAll operations completed\n"

.align 2

.text
.globl main


main:	             # program entry

    la $a0, welcome
    li $v0, 4
    syscall
    j file_prompt

file_prompt:         # Prompts & stores file path

    la $a0, NamePrompt
    li $v0, 4
    syscall

    li   $v0, 8                #take in input
    la   $a0, FileNameBuffer   #load byte space into address
    li   $a1, 500              # allot the byte space for string
    syscall

    jal scrubber_subroutine  # $a0, $a1 contain proper info

out_prompt:          # Prompts & stores output path

    la $a0, OutPrompt
    li $v0, 4
    syscall

    li   $v0, 8                # take in input
    la   $a0, OutputBuffer     # load byte space into address
    li   $a1, 500              # allot the byte space for string
    syscall

    jal scrubber_subroutine    # $a0, $a1 contain proper info 

create_file:         # Create file that doesn't exist


    li   $v0, 13
    la   $a0, OutputBuffer  # output file name $a0 already contains buffer
    li   $a1, 256          
    li   $a2, 0x1FF      
    syscall        
    move $s6, $v0      # save the file descriptor

    # Close file for now
    li   $v0, 16           # system call for close file
    move $a0, $s6          # file descriptor to close
    syscall                # close file

pass_prompt:         # Prompts & sores passphrase

    la $a0, PassPrompt
    li $v0, 4
    syscall

    li   $v0, 8                # take in input
    la   $a0, PassBuffer       # load byte space into address
    li   $a1, 512              # allot the byte space for string
    syscall

    jal scrubber_subroutine    # $a0, $a1 contain proper info 

file_opening:        # Opens read & write files

    li     $v0, 13             # Open source file for reading
    la     $a0, FileNameBuffer           
    li     $a1, 0              # 0 for read
    li     $a2, 0              # Mode ignored
    syscall           
    move   $s6, $v0            # $s6: INPUT FILE descriptor

    li   $v0, 13             # Open output for writing (a file that does yet not exist)
    la   $a0, OutputBuffer   # output file name buffer
    li   $a1, 257            # 257 Creates file and allows writing
    li   $a2, 0x1FF          # Read/write/execute permissions
    syscall       
    move $s7, $v0            # $s7: OUTPUT file descriptor

encrypt_decrypt:     # Encrypts/Decrypts file
        
    move $s1, $zero   # $s1: passBuffer index
    file_read:        # Reads file to buffer

        #read from file
        li   $v0, 14           # read file
        move $a0, $s6          # file descriptor 
        la   $a1, FileBuffer   # Read into this buffer
        li   $a2, 1024         # Max chars to read
        syscall                # read from file

        move $s5, $v0          # Store amt. of bytes read
        beqz $s5, end

        move $s0, $zero        # $s0: fileBuffer index
        
        encrypt_loop:

            lb   $t1, FileBuffer($s0)    # Load character at index in FileBuffer
            lb   $t2, PassBuffer($s1)    # Same as above but in PassBuffer
            beq  $t2, $zero, reset_ctr   # If null character reached, reset password ctr
            ctr_cont:
            beq  $t1, 127, restore_sub
            restore_cont:
            xor  $t0, $t1, $t2           # XOR Operation
            beq  $t0, 26, remove_sub
            remove_cont:
            sb   $t0, FileBuffer($s0)    # Store result
            beq  $s0, $s5, encrypt_exit  # If end of buffer reached, exit
            addi $s0, $s0, 1             # Increment fileBuffer index
            addi $s1, $s1, 1             # Increment passBuffer index
            j    encrypt_loop            # Loop

            # When encountering a DEL character, replace with SUB 
            restore_sub:
            li $t1, 26
            b restore_cont
            
            # When encountering a SUB character, replace with DEL
            remove_sub:
            li $t0, 127        
            b remove_cont

            # Password index reset
            reset_ctr:
            move $s1, $zero        # $s1: passBuffer index
            b ctr_cont

        encrypt_exit:     

    write_buffer:

        # Write to file opened earlier
        li   $v0, 15            # system call for write to file
        move $a0, $s7           # OUTPUT file descriptor 
        la   $a1, FileBuffer    # address of buffer from which to write
        move $a2, $s5           # NUM CHARACTERS TO WRITE
        syscall                 # write to file

        #beqz $v0, end

        j file_read

end:                 # Ends program

    li   $v0, 16   # Only need to load 16 once for both close operations

    move $a0, $s7  # Close the OUTPUT file   
    syscall          
    
    move $a0, $s6  # Closing INPUT FILE    
    syscall          

    
    la $a0, doneMsg # Simple message
    li $v0, 4
    syscall  

    li $v0, 10		# terminate the program
    syscall



scrubber_subroutine: # Removes line return character
#   INPUT:   $a0 - pointer to buffer to scrub '\n' out of and replace with $zero
#            $a1 - Max size of string buffer
# RETURNS:   Nothing - buffer will be changed in memory

     li $t1, 0        # Counter to 0

    scrub:

        lb   $a3,   0($a0)            # Load character at index
        addi $a0,   $a0, 1            # Increment index
        addi $t1,   $t1, 1            # Increment counter
        bnez $a3,   scrub             # Loop until the end of string is reached
        beq  $a1,   $t1, exit_loop    # If string fills buffer, do not delete anything
        subu $a0,   $a0, 2            # If above false, reverse index to '\n'
        sb   $zero, 0($a0)            # Replace with null terminator

    exit_loop:
    
        jr $ra # RETURN 
