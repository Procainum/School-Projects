# Writing all ascii chars to a file, used for testing purposes

.data
filename:   .asciiz "C:\\Temp\\chars.txt"
OutputBuffer:       .space      256

.align 2

.text
.globl main


main:	          # program entry

  # Create file that doesn't exist
    li   $v0, 13
    la   $a0, filename  # output file name $a0 already contains buffer
    li   $a1, 257          
    li   $a2, 0x1FF      
    syscall        
    move $s6, $v0      # save the file descriptor

    move $s0, $zero
    loop:

        sb   $s0, OutputBuffer($s0)    # Store result
        addi $s0, $s0, 1
        beq $s0, 256, write_buffer
        j loop

  write_buffer:

        # Write to file opened earlier
        li   $v0, 15            # system call for write to file
        move $a0, $s6           # OUTPUT file descriptor 
        la   $a1, OutputBuffer    # address of buffer from which to write
        li $a2, 256           # NUM CHARACTERS TO WRITE
        syscall                 # write to file

    # Close file for now
    li   $v0, 16           # system call for close file
    move $a0, $s6          # file descriptor to close
    syscall                # close file

    li $v0, 10
    syscall