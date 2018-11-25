<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Photo Upload</title>
    <meta name="description" content="Simple file uploading to server">
    <meta name="author" content="Sebastian Vivanco">
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        jQuery(function($) {
            $(".file-selector").on('click' , function(){
                $(this).val('');
            });

            $(".file-selector").change(function(e) {
                $(this).siblings('.tags-container').show();
                $(".button").show();
            });

            var button = $(".log-button");
            var phpEcho = $(".php-echos");
            phpEcho.hide();
            var fadeIn = function () {
                phpEcho.show(0);
                phpEcho.animate({width: "550px"});
            };
            var fadeOut = function () {
                phpEcho.fadeOut(500);
                phpEcho.css("width", 0);
            };

            $(".log-button").click(function () {
                phpEcho.is(":hidden") ?  fadeIn() : fadeOut();
            });
            if(phpEcho.html().length > 5 ? button.show() : button.hide());
        });
    </script>
</head>
<header>
    <div class="tooltip"> <button type="button" class="log-button">+</button>
        <span class="tooltiptext">Show/Hide Log</span>
    </div>
</header>
<form enctype="multipart/form-data" name="uploadForm" method="post" action="upload.php">
    <div class="flex-container">
        <div class="inner-container">
        <div class="box">

            <h1>Image One</h1>
            <input class="file-selector" type="file" name="file1" id="file"/><br><br>

            <div class="tags-container">
                <p>Enter 3 descriptive tags for your image:</p>
                <input type="text" name="tag1[]" placeholder="Tag 1" />
                <input type="text" name="tag1[]" placeholder="Tag 2" />
                <input type="text" name="tag1[]" placeholder="Tag 3" />
            </div>
        </div>

        <div class="box">
            <h1>Image Two</h1>
            <input class="file-selector"  type="file" name="file2" id="file"/><br><br>

            <div class="tags-container">
                <p>Enter 3 descriptive tags for your image:</p>
                <input type="text" name="tag2[]" placeholder="Tag 1" />
                <input type="text" name="tag2[]" placeholder="Tag 2" />
                <input type="text" name="tag2[]" placeholder="Tag 3" />
            </div>
        </div>

        <div class="box">
            <h1>Image Three</h1>
            <input class="file-selector" type="file" name="file3" id="file"/><br><br>

            <div class="tags-container">
                <p>Enter 3 descriptive tags for your image:</p>
                <input type="text" name="tag3[]" placeholder="Tag 1" />
                <input type="text" name="tag3[]" placeholder="Tag 2" />
                <input type="text" name="tag3[]" placeholder="Tag 3" />
            </div>
        </div>
        </div>
        <div class="button">
            <br><br><input type="submit" name="submit" value="Upload Files"/>
        </div>
    </div>

</form>

<div class="php-echos">
    <?php
        if (isset($_POST["submit"])){
            $counter = 1;
            foreach($_FILES as $file_key => $file_info_array) {

                //Accessing the file information
                $file_name = $file_info_array["name"];
                $file_temp_location = $file_info_array['tmp_name'];

                if($file_name == "") { $counter++; continue; }

                $folder_name = "./uploads/";
                $text_file_name = $file_name.".txt";

                $tag_name = "tag".$counter;
                $tags = $_POST[$tag_name];
                $tags_formatted = join(', ', $tags);
                file_put_contents($folder_name.$text_file_name, $tags_formatted);

                $allowed =  array('gif','png','jpg','GIF','PNG','JPG');
                $ext = pathinfo($file_name, PATHINFO_EXTENSION);
                if(!in_array($ext,$allowed) ) {
                    echo "File " . $counter . " doesn't have an allowed file type!<br><br>";
                    continue;
                }

                echo "Processing: " . $file_key . "<br>";
                echo "Processing: " . $counter . "<br>";

                //echoing the file information
                echo "File name: " . $file_name . "<br>";
                echo "Temporary location: " . $file_temp_location . "<br><br>";

                //Moving the file to a permanent location on the server

                $destination = "./uploads/" . $file_name;

                if (move_uploaded_file($file_temp_location, $destination)) {
                    echo "The file ". $file_name . " has been uploaded.<br><br>";

                }
                else {
                    echo "Sorry, there was an error uploading your file.<br><br>";
                }

                $counter++;
            }
            echo "Done";
        }
    ?>
</div>
<footer>
    <p>Created by Procaine</p>

</footer>
</body>
</html>

