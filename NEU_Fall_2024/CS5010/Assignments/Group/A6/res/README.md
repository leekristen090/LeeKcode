# Assignment 6

## Design Changes

* In assignment 5, the only file in our view package was Main.java, which would call either the 
script or interactive based on what the user prompted.
    * We added 6 new files:
        * Features.java --> the various features a user can expect to see within the GUI
        * FeatureController.java --> implements the features found in Features.java
        * ImageGUIFrame.java --> implements the IView and extends the JFrame creates the various 
      sections of the GUI, ex: buttons, sliders, popups, histogram, image
        * IView.java --> the main features a user can expect to find in the GUI
        * ImageGUI.java --> connects the ImageController to the FeatureController and creates the 
      GUI window
        * Main.java --> allows users to either launch the GUI or input command-lines

* Major changes to Compression.java
    * The Compression.java file from assignment 6 contained the related methods needed to 
  compress an image. However, the code within these methods was not accurate. We restructured our 
  existing code to ensure that an image would be successfully compressed.

* Changes within CommandFactory.java
    * We changed the method signature of applyLevels() and applyCompression() from private to 
  public. In doing so, we were able to directly assess these methods within ImageGUIFrame.java 
  and pass the corresponding parameters into them.

## Image Citations
mitra.jpg -> https://northeastern.instructure.com/courses/192553/assignments/syllabus

landscape.jpg -> 
https://www.nyip.edu/photo-articles/photography-tutorials/the-best-place-to-focus-in-a-landscape

## Graphical View

### Graphical User Interface

Your graphical user interface should have the following characteristics, and obey the following 
constraints:

- [x] Use Java Swing to build your graphical user interface
- [x] GUI should show the image that is currently being worked on
    * The image may be bigger than the area allocated to it in your graphical user interface
    * In this case, the user should be able to scroll the image
    * Any changes to the current image as a result of the image operations should be visible in the 
  GUI
- [x] Histogram of the visible image should be visible as a line chart on the screen at all times
    * If the image is manipulated, the histogram should automatically refresh
    * The histogram should show the red, green, blue components
- [x] User interface must expose the features described in the next section
- [x] When saving an image as a PNG/PPM/JPG, it should save what the user is currently seeing
- [x] The user should be able to specify suitably the image to be loaded and saved that the user 
is going to process
    * The program cannot assume a hardcoded file or folder to load and save
- [x] Any error conditions should be suitably displayed to the user, through pop-up messages or 
clearly visible text as appropriate
- [x] The layout of the UI should be reasonable
    * Things should be in proper proportion, and laid out in a reasonable manner
    * Buttons/text fields/labels that are oversize, or haphazardly arranged, even if functional, 
  will result in a point deduction
- [x] Each user interaction or user input must be reasonably user-friendly (e.g. making the user 
type something when a less error-prone method is possible is not good UI design)
    * Our standard is: can a user unfamiliar with your code and technical documentation operate the 
  program correctly without reading your code and technical documentation?
- [x] Keep in mind that this is a graphical user interface for your program
    * It is not a graphical way to use script commands
    * The expectations of the user, and what the user is expected to enter, are not the same as 
  when specifying script commands

### Features exposed through UI

The following features must be usable through your graphical user interface:

- [x] The user is expected to work on one image at a time, the image that is visible in the UI
- [x] Load an image in PPM/JPG/PNG formats
    * If the currently shown image is not saved, the program should prompt the user accordingly
- [x] Save the currently visible image in PPM/JPG/PNG formats
- [x] Visualize the red/green/blue components of an image (the shown image should change 
accordingly)
- [x] Flip the image vertically or horizontally
- [x] Blurring the image
- [x] Sharpening the image
- [x] Converting the image to greyscale using luma
- [x] Converting the image to sepia
- [x] Viewing the image with compression artifacts
    * The user should be able to enter the compression factor in some way
    * It is reasonable to expect the user to enter only integral percentages, although this 
  restriction is not required
- [x] Viewing the color-corrected version of this image
- [x] Adjust levels of this image
    * The user should be able to enter the black, white and mid-values in some reasonable way
    * You are not required to support the traditional "drag-a-point" interface that was illustrated 
  in Gimp
- [x] Be able to toggle between showing a split view and the entire image, with a way to enter 
the split percentage in a reasonable way
    * Changing the split percentage should show the modified result quickly, as this is the point 
  of viewing an operation in split view
    * This should only affect certain operations as before

### View and Controller

Carefully design the interaction between a view and a controller, and formalize the interactions 
with view and controller interfaces. You may design a single controller that manages the program 
in script mode and GUI mode. Different controllers for different views are also possible if the 
views are very different from each other. However, be mindful of the MVC principles and separation 
between the model, view and controller. When designing, always ask: "can I change one part with 
no/minimal changes to the others?"

### Testing

**_Testing the actual GUI is optional_**

However, you should test whether the controller does what it is supposed to in reaction to this 
happening.

### Create a JAR File

See directions on Canvas

## What to Submit

zip file should have 3 folders, src, test, and res
- [x] src folder
- [x] test folder
- [x] res folder
    - [x] JAR file -> should be able to run your program from this jar file
    - [x] Script.txt --> script that reads in various image commands
    - [x] mitra.jpg
    - [x] landscape.jpg
- [x] Screenshot of program with an image loaded
    * You do not need to submit multiple example images, but submitting a sample image to try out 
  your program is required
- [x] readme
    * Documents how to use your program, which parts of the program are complete, design changes 
  and justifications, and citations for all images
- [x] useme
    * Contains a bullet-point list of how to use your GUI to use each operation supported by your 
  program
    * Screenshots would be helpful, but not necessary