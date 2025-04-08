# README for Assignment 5

## Changes made to code from assignment 4:

* In assign 4, our model was very tightly coupled with the controller, there was a double dependency 
where the model called/instantiated the controller, and the controller directly called the model
    * We addressed the issue of calling the controller from the model by removing controller 
instantiation in the model classes
* Creation of packages
    * Allowed us to more easily identify which classes worked together and how they should interact 
with other classes
* In assignment 4, we had array to hold our manipulated images with their corresponding aliases
    * This was done to ensure that we did not overwrite images when doing multiple manipulations
    * The array size had to be declared and if we added more than the array allowed, we wouldn't 
have anywhere to put our manipulated images
    * We changed this to a hashmap to account for this issue
* Changing the abstract AbstractImage class to a concrete class called SimpleImage
    * In our project, we were not using the abstract image class as a true abstract class
* Created command factory to hold logic to apply the image manipulations
    *
* Created command registry to create a hash map of commands allowing us to decrease the number of 
switch cases/if-else statements
    * This also helps us with maintainability as we will only have to add a command to the hash map 
if we need to add additional manipulations
* Script executor to read a script and execute commands
    * Our script executor class holds the logic for reading a script file from a given script path
    * It then reads and executes the given commands in the file
* Interactive class
*

## Controller

In our controller we have two interfaces, Command and Controller. From that we have ImageController 
and separate Command classes which implement those interfaces respectively. We also have our 
Interactive and ScriptExecutor in the controller package.

## Model

Our model package holds separate classes for each image manipulation logic. For example, Sepia.java 
holds the logic to do sepia manipulation on an image using the given sepia matrix. Blur.java holds 
the logic for blurring an image, and so on. We also have Image interface and SimpleImage. These hold 
logic for our image pixel data.

## View

In this package, we have our Main.java. In main this is were we either call the script executor or 
the interactive class. When calling the script executor, this is when you ONLY want to read the 
script with no command line interaction/user input. When calling interactive, this allows for the 
command line interaction/user input. One of those user inputs also includes choosing to run a script 
file. This can be done by using the command "-file yourScriptFilePath.txt" where 
'yourScriptFilePath.txt' is the path to your script file that you want to run.

# Assignment 5 Submission Requirements

Below you will find all the requirements for assignment 5 submission as found on the Canvas page.

## Image Compression

<ins>Requirements for image compression:</ins>
- [x] avg and diff
- [x] compression of sequence
- [x] 2D harr wave
- [x] compression of images
- [x] manipulation supported by script command
    * "compress percent image dest" where percentages 0-100 are valid

## Image Histogram
<ins>Requirements for image histogram stuff:</ins>
- [x] histogram visualization
    * produce image that represents histogram of given image
        * this will be a 256x256 image
        * contain the histograms for the red, green and blue channels as line graphs
    * supported by script command "histogram image-name dest-image-name"
- [x] color correction
    * ability to color-correct an image by aligning the meaningful peaks of its histogram
    * supported by the script command "color-correct image-name dest-image-name"
- [x] levels adjustment
    * ability to adjust levels of an image
    * supported by the script command "levels-adjust b m w image-name dest-image-name"
        * where b, m and w are the three relevant black, mid and white values respectively
        * These values should be ascending in that order, and should be within 0 and 255 for this 
command to work correctly
* The grid pattern shown in the examples is optional, but helpful ( Hint : the BufferedImage class 
may be helpful, specifically because it provides an ability to draw on it)

## Split Preview
<ins>Required operations that must support an optional split preview manipulation:</ins>
- [x] blur
- [x] sharpen
- [x] sepia
- [x] greyscale
    * on canvas it only says greyscale but still did luma, value, intensity in case
    - [x] value
    - [x] luma
    - [x] intensity
- [x] color-correction
- [x] levels-adjust

## Extra things to do
### Testing
- [x] test the model
- [x] test the controller

### JAR file
- [x] create JAR file of the program using directions foung on canvas.

### Run script file from command line
- [x] "-file scriptname.txt"

# Required for Submission
- [x] src folder
- [x] test folder
- [x] res
    - [x] images
        - [x] source image (any supported format)
            - [x] source image histogram
        - [x] 2 compression images
            - [x] 2 compression histograms
        - [x] color correction
            - [x] color correction histogram
        - [x] 2 levels adjust images
            - [x] 2 levels adjust histogram
        - [x] 2 split view images
            - [x] 2 split view histograms
    - [x] example script
        * script file shows all the working features of your application
        * If a command is missing from your example file, we will assume that it does not work and 
grade accordingly
        * Your script file should run as-is when we run your JAR file with the command-line option,
so please write and place it accordingly
    - [x] JAR file
        * should be able to run your program from this jar file
    - [x] updated UML
- [x] readme
    * README file should document which parts of the program are complete, and design changes and 
justifications
- [x] useme
    * USEME file should summarize which script commands are supported by your application, examples 
of using them