# Critique of provider's code

The GUI is generally straightforward to use, as the buttons are clearly labeled for the user. 
We noticed a few issues and had small suggestions that could be added to the GUI's 
functionality. Assignment 6 stated that if a user attempts to leave the program without 
saving their unsaved changes on the loaded image, they should be prompted to save the image 
first. This was not a feature for either loading in an entirely new image while a previous 
image had unsaved changed or for exiting out of the program entirely. We also noted that if a 
user wanted to undo their effects and revert to the original image, they would need to load the 
image in again. This can be inefficient, especially if a user wants to use the same image but 
wants to alter the effects that have been applied. The vertical slider bar was a nice 
feature, as it allows the user to focus more on either the image or the histogram. However, 
there is a large amount of white space when the vertical slider is moved, thus making the space 
look emptier. It may be better to resize the image accordingly as the vertical slider is moved 
to avoid the excessive white space for the image and histogram plots. Lastly, we noticed that 
for the Adjust Color, Compression, and Downscaling features, the programs prompts the user for
values using text boxes. It would be better to use another feature, such as a slider, to ensure 
there is a decreased chance of user error when inputting these values.

When implementing dithering into the code, we used the ImageProcessing interface and the 
ApplyChannel and ApplyKernel abstract classes. We initially began by using our own dithering 
class to extend ApplyChannel, as the first step of dithering is to convert each pixel value 
into intensity greyscale. However, we later began to realize that we would also be expected 
to apply a kernel to the pixels, specifically the error calculated previously with the 
corresponding fractions for the correct surrounding pixels. However, in changing our code to 
extend the ApplyKernel abstract class instead, this would clash with the intensity greyscale code.
We determined it would be easier to simply implement the ImageProcessing class itself. 
However, we wondered if there could be a different way to structure the code, so that if another 
class is required to apply a channel and then apply a kernel to an image, both abstract classes 
could somehow be used rather than having to implement ImageProcessing directly. Aside from these 
issues, we noted that the code was generally straightforward and easy to read. Each package 
contained classes that were relevant to its purpose. If a user wanted to trace through the actions 
of loading in an image and, for example applying the sepia filter to it, the code clearly 
references which classes to look at to complete these actions. We also noted the separate classes 
for PPM images and other images, and liked the distinct separation for these as PPM images are 
loaded and saved differently from JPGs, JPEGs, PNGs, etc.

While some classes were documented well, others were left extremely vague. We noticed some places 
where Java docs would either be "." or very uninformative. For example, the constructor for the 
ImageDisplayPanel class has a Java doc that only says "Constructor method". If a user unfamiliar 
with Java or the purpose of the project were to look at this, they would likely have trouble 
trying to determine what the purpose of the constructor is and how it could be used throughout 
the code. We also noticed that the working code itself could be commented more so that individuals 
looking through the code will be able to understand some of the methods and actions better.

Overall, this code was very cleanly written and easy to use. As stated before, a user could 
easily understand how to use and implement different methods found within this project. All 
files were well organized within the project structure and placed in correct packages for easy use. 
It was simple to add a new functionality to this project and there were very little issues. We 
had a great time reviewing and working with the providers' code.