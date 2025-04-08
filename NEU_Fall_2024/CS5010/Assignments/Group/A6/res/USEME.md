# Assignment 6 USEME
How to use each operation in our GUI.

## To run our program 
This can be done by either simply running the main in IntelliJ, which will run the GUI, or it 
can be run with the JAR file. To run JAR, open a terminal and navigate to the folder that holds
the JAR file. Use the following commands:

### **To run GUI through JAR:**

java -jar nameOfJar.jar 

Once running, do desired GUI things.

### **To run script through JAR:**

java -jar nameOfJar.jar -file scriptPath.txt

Once program reaches end of script, it will exit


### **To run interactive through JAR:**

java -jar nameOfJar.jar -text

In the terminal, you will be able to type your desired commands. To exit the interactive/program,
type exit.

# GUI Components
## Load
* After clicking load button, the user will see a window in which they can navigate to their image,
or where the image they desire is located
* Once the user has found the image they wish to load, they can double-click the image file, or 
press open to load their image
* If the image is larger than the image window, they will have the ability to scroll their full 
image
* After that is done, the image will appear in the main window along with the associated histogram

## Save
* When clicking save, this allows the user to save the visible image
* After clicking the save button, the user will see a window to navigate to the file path they wish 
to save their image to
* They also have the ability to save the image under a name of their choosing
* When they have navigated to their desired save location, they can click save in the file chooser 
window

## Clear Effects
* By clicking this button, the effect(s) that have been applied to the visible image will be removed
* The user will be left with the original image that they loaded

## Split Preview Checkbox and Slider
* This checkbox and slider is the way we chose to implement split view in our gui
* The user will check split view if they wish to split preview the operation on their image
* If the image effect they chose previously does not support split preview, a popup window with an 
error message will show up saying that image effect is not supported for split
    * If the image effect does support split, then the user can use the slider to indicate where 
they would like to see the split on the image (Ex. if the slider is at 50, the image effect will 
be applied to half of the image)
* The left side will show the given image effect keeping the right side the original image
* When split view is not checked, the image effect will be fully applied to the image

## When Clicking Exit in Top Left Corner
* When clicking the x button on the GUI window, which is in the top left hand corner, the user 
will be prompted with a window being asked if they wish to save the visible image and given options 
cancel, no, and yes.
    * If they click yes in the popup window, the user will see a file chooser the same as with the
save button
        * The user can name their image and navigate to their desired location.
    * If they choose no, the program will exit without saving the visible image
    * If they choose cancel, they will return to the GUI

## Effects in side panel
* When an image has been loaded, the user can click any of the buttons in the effect panel to 
apply the image effect to the visible image
* The visible image will update to show the chosen effect and the histogram will be updated 
accordingly with the red, green, and blue components

### Adjust Levels Effect
* The user can click this button to adjust the black, mid, and white values of their image
* Once this button is clicked, the user will be prompted with a popup window with three sliders 
allowing the user to choose black, mid and white values
* Once user moves sliders to desired value for each component they can click ok in the popup 
window and the effect will be applied
* This effect supports split preview so they can use the split checkbox and slider as described 
above

### Red, Bue, and Green Effects
* When clicking these buttons, the user can visualize the image in the red, green, and blue 
components
    * Red meaning at each pixel, the red channel of the original image will be applied to all 
three color channels for that pixel
        * This will be applied to all pixels in the image
    * Green meaning at each pixel, the green channel of the original image will be applied to all 
three color channels for that pixel
        * This will be applied to all pixels in the image
    * Blue meaning at each pixel, the blue channel of the original image will be applied to all 
three color channels for that pixel
        * This will be applied to all pixels in the image
* The result of these effects will be a grey image
* These effects do not support split preview

### Blur Effect
* User can click the blur button to apply the blur image effect to their image
* This effect supports split preview so they can use the split checkbox and slider as described above

### Color Correct Effect
* User can click the color correct button to apply the color correct image effect to their image
* This effect does not support split preview

### Compression Effect
* When the user clicks the compression button, they will be prompted with a popup window to 
choose the percentage to which they would like to compress their image
    * The percentage will be chosen with a slider
    * Once the value is chosen, they can click ok on the popup window and compression will be 
  applied
* This effect does not support split preview

### Flip Horizontal and Flip Vertical Effects
* These buttons will flip the user's image along the axis for according to the button they clicked
    * Flip Horizontal will flip the image along the horizontal axis and vertical will flip 
over the vertical axis
* This effect does not support split preview

### Greyscale-Luma Effect
* User can click the greyscale-luma button to apply the luma image effect to their image
* This effect supports split preview so they can use the split checkbox and slider as 
described above

### Sepia Effect
* User can click the sepia button to apply the sepia image effect to their image
* This effect supports split preview so they can use the split checkbox and slider as 
described above

### Sharpen Effect
* User can click the sharpen button to apply the sharpen image effect to their image
* This effect supports split preview so they can use the split checkbox and slider as 
described above