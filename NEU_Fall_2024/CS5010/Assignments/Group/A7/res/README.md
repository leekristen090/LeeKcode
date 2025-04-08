# Assignment 7 README
Dither an image implementation: Y

Script command to dither an image: Y

Dither an image from GUI: Y

To implement dithering, we created a new class/file in the model package that holds the logic 
for applying dither to the pixels of an image. First we converted the image to a grey scale 
image using the intensity value in a private helper method. We then applied the Floyd-Steinberg 
dithering algorithm to generate and return the image with dither applied. To do so, we studied 
the given general code on canvas then read further on the linked website given to us. 
Determined whether a pixel's red value (could also use green or blue, as it is greyscale) was 
closer to 0 or 255, set the pixel to that value, and determined the error (old value - new value). 
Applied the error according to pixels surrounding current pixel. Set each corresponding 
pixel in image.

In the Features interface we added a void method for dithering which allowed us to implement 
dither in GUIController to process the command and update the view.

In FileControllerImpl, we added dither to the command map to ensure that dither can be 
registered as an available image processing command.

In ToolPanel, we first added the dither button to the GUI environment and then added an action 
listener to allow for the manipulation to be applied.

## Note
In the res folder, there is a folder holding the script files. There are two script files 
ScriptAll.txt, which was the given script file from the provides. There is Script2.txt, which is 
the script that we implemented.

# Image Citations
mitra.jpg -> https://northeastern.instructure.com/courses/192553/assignments/syllabus

landscape.jpg ->
https://www.nyip.edu/photo-articles/photography-tutorials/the-best-place-to-focus-in-a-landscape

# Below is the assignment 7 description from canvas:
## What to do

* Implement and test image dithering only in the code provided to you
* Add support to specify image dithering through script in the code provided to you:
    * dither source-image-name dest-image-name
* Add support for image dithering through the GUI in the code provided to you. It should be 
possible to specify the operation, preview the image using a split view, and apply it to the 
current image
    * As before it should be possible to view the result and save it to an image file
* Write a critique of the provided code

## Code Critique
As customers of the providers' code, you have the opportunity to praise, critique, comment upon, 
and suggest improvements to their code. In short, you should provide a code review of their code.

In that review, consider all the self-evaluation questions you have been asked during this course, 
and extrapolate from them the kinds of issues to examine. For example:
* How flexible are the interfaces you were given?
* How good was the implementation?
* What are some specific strengths of their design and/or implementation?
* What are some specific limitations of their design?
* How convenient was it to use their code?
* How cleanly written and well-documented was their code?
* If you needed to request changes from them, what were they and why were they needed?

Write a short (3-4 paragraph) review of their code. Your review should have the following sections:

Design critique, implementation critique, documentation critique, design/code strengths, 
design/code limitations and suggestions on how to address them.

## Dithering
Use the Floyd-Steinberg dithering algorithm (see canvas).

## Submission
- [x] Submit all files necessary to make your code work (this includes the code you got from 
your providers, with code you wrote for this assignment)
- [x] Include in your submission at least one original image with its dithered counterpart
  * You may not use any images given to you by your providers
- [x] Submit your review of your providers' code (i.e. the critique)
- [x] Submit a JAR file (with extension .jar) file that can run the program with the same 
command-line options as stipulated in the previous assignment
- [x] README that specifically
  * Describes how you implemented image dithering to be in harmony with the design given to you 
  (i.e. how you managed to fit the new feature in the existing design)
  * Also indicate at the top of this file whether you were able to implement image 
  dithering correctly, supported a script command for it and exposed it through the GUI 
  (in the same style as the questionnaire you sent to your customers)