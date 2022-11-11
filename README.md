README:

**Changes:**

_Controller_:
We changed the controller to now have private save, load, and applyOperation methods, as well as 
having a map of file writers. We saw that the model shouldn't be responsible for IO (like saving and loading,
in the ImageState and ImageEditor respectively with the .save() on ImageStates and .importFromDisk() on ImageEditor).
We realized that the controller should handle user input and know how to operate the model,
so we refactored our code to move saving images from the model and loading images to the controller,
which would handle user input and save or load accordingly. Additionally, our old ImageEditor model
has an .applyFilterAndSave() method which took in a filter and saved the image with the filter applied with the new name.
We realize this isn't great design, since the controller should use the model in that way (get an image, apply a filter,
and save it). We refactored this to have a .applyOperation() method in the controller, does a look up in the map,
makes a new image, and tells the editor to save it. This way, the model doesn't have any logic about how it should be used,
and that is now moved to the controller.

_Model_:
We changed several things in the model. For starters, ImageState interface now has only one sub class, 
BasicImage. We changed this from our prior approach of AbstractBaseImage and ImagePPM because we realized
that the controller should handle IO, and if the image isn't handling IO, then it has no need to care
if it is a PNG or a PPM image, it should just store pixels. Thus, we removed the .save() method from image as well
as mentioned above and shifted that responsibility to the controller. Related to images, we removed the IPixel interface
in favor of an RGBAPixel concrete class. We realized that having an IPixel interface was unecessary, since
ultimately every pixel had methods to get RGB&A, so we could just represent a pixel as a concrete class, with 
multiple constructors, one for grayscale, one for RGB, and one for RGBA. Finally, for ImageState,
we added a method asBufferedImage() which we would use for converting images to buffered images for saving in the controller.
We thought this was a reasonable change since in order to write images to the disk, we would have to use ImageIO,
which uses buffered image, so having every image be able to make a bufferedimage out of itself is convenient.
On that note, we also added a new constructor that takes a buffered image in favor of our old constructor
that took in a 2D array of pixels. We realized that we would have to convert the buffered image to a 2D array
of pixels anyway, so we might as well just take in a buffered image and convert it to a 2D array of pixels in the image
constructor.

For the ImageEditor, we decided to remove the .importImageFromDisk() method, and the applyFilterAndSave() method.
As mentioned above in the controller justification, we thought IO for importing from disk was more the controller's
responsibility, so we moved that functionality and dumbed down the editor model. We also removed the applyFilterAndSave()
method because we thought that the controller should be responsible for applying filters and saving images, not the model.

Overall, additionally we made a few small modifications here and there related to our switch from IPixel to RGBAPixel,
and to using BufferedImage in constructors, which we won't cover here, but we thought were reasonable changes that accompanied
our other ones.


**High level Overview:**

**Model:**

We separated the model into two pieces: the model for an image, and the model for the image editor.
This was done because an image knows how to store pixel data, and an image editor know how to store
images. This way, we could easily break up and delegate the work to the specific classes for the
suited tasks. As for how we designed these classes, we made the BasicEditor just implement the
interface directly, and implement all the methods. We moved two of our methods out of the ImageEditor
interface and into the controller. The two methods were importFromDisk, and applyFilterAndSave.
Both of these felt like things the controller should be doing to the model, if the model were just
a dumb database of images. 

For the images, we decided to change our previous design from Abstract base class and ImagePPM, etc 
subclasses to instead use one general base class. This was done because we realized that the only difference 
between the subclasses was the way
we save data to the disk, which shouldn't be the responsibility of the model to figure out, but rather
the controller. Our images and pixels are still all immutable, so every operation on pixels or images returns
either a new pixel or a new image, to avoid mutation errors, which is something we didn't change in our design.

We chose in an extension to that design to have images be a board of RGBAPixels, where each RGBAPixel
is a pixel with a red, green, blue, and alpha value. This was done because we wanted to be able to
support standard image formats, as well as with transparency. We changed this from our previous design of the 
IPixel interface because we realized that since the IPixel interface mandated getRed, getGreen, etc.,
there was not really room for additional implementations of IPixel that were sufficiently different
to require a fully fledged interface and a new implementation. We instead included constructors
for RGBAPixel that would let you construct grayscale, RGB, and RGBA images, to simulate the diversity
of IPixels we had in the previous assignment. We think this was a good design decision because
with multiple IPixel implementations, there was too much duplicated code and logic, and it was
generally just cluttered. 

**Controller:**

As for the controller design, we just implemented a controller with just one public method start.
Internally we represented the controller as a map of string commands to function objects, and told
the editor to apply a function object to a particular image. We also included save and load methods
in the controller, which would save and load the image to a file. We had additional helper methods
to reuse code for applying filters and saving into the model. This was a change that we made from the previous assignment,
where our editor model contained methods for loading from disk and for applying filters and saving.
Now instead, we realized that those IO operations that take user input should really be in the controller,
since it is the one directing the model to do things. 

**Commands:**

As for the commands, we just made a simple PixelOperation command interface to represent all of our
commands. We chose this to be an Image, int, int to Pixel, so we could support operations that don't
just require one pixel's data (like the kernel's). For the new commands like Kernels and Color operations,
we made new classes that implemented the PixelOperation interface (KernelOperation and ColorOperation).
We thought these were justified since Kernel operations take in more data than just one pixel,
and all perform a similar for loop, and color operations just take in one pixel's data but then perform
color transformations on the components. 


**What is complete?**
The BasicImage and BasicImage editor are both complete. They should support extension instead of modification,
since all they have is model functionality. The controller is complete for now, and supports all the commands
specified by the assignment, and all loading/saving functionality for the image types we want to support.
The commands are complete, and support all the operations specified by the assignment (and are easily
extensible, by just creating a new implementation of the PixelOperation interface).
The view is incomplete, and does nothing at the moment. RGBAPixel is also complete,
and supports all the functionality that we need it to for the assignment.


**Walk through of Packages**

As such, in the **controller** package:
ImageEditorController is the interface for the controller.
BasicEditorController is the basic version of the controller, which supports all the commands
that we made for the two assignments (and takes in a view but doesn't yet do anything meaningful with it
yet).

In the **model** package:
The ImageState model which represents an Image.
The RGBAPixel class which represents a pixel with RGBA values.
The ImageEditorReadOnly and ImageEditor represent the read only and the editable version of the
editor respectively, which would maintain the map of images internally and allow the user to
interact with the model through the controller to add or remove images from the map.
BasicImage is the only implementation of the image state interface, and handles most
generic operations like applying a filter to an image, constructing images, getting pixels at
positions, and getting width/height, and now contains no save functionality (Read above).
ImageUtil is the class provided to us in the starter code and is used to load PPMs from the disk.

In the **operations** package:
The only interesting thing here is the PixelOperation, which is essentially a
TriFunction which takes an Image and a row and column and produces a new Pixel. This exactly fits all of
our needs for commands like flip or darken, because we can use other information from the image,
other than just the particular pixel hole we are trying to fill in.
All the commands follow a similar format, where they get a particular pixel, manipulate it, and
return a new one. See Javadocs for detailed purpose statement for all of those, but they are all
just the commands that were requested for this assignment.

In the **view** package:
There is nothing really in here other than view stubs that do nothing.

Commands the editor will accept:
load path name
save path name-to-save
brighten val name name-to-save
darken val name name-to-save
vertical-flip name1 name-to-save
horizontal-flip name1 name-to-save
value-component name1 name-to-save
intensity-component name1 name-to-save
luma-component name1 name-to-save
red-component name1 name-to-save
green-component name1 name-to-save
blue-component name1 name-to-save
blur name1 name-to-save
sharpen name1 name-to-save
sepia name1 name-to-save
grayscale name1 name-to-save

How to run:
Type the commands you want to execute into the terminal.

Example Script: see res/script.txt




