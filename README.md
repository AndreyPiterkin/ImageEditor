README:

High level Overview:

We separated the model into two pieces: the model for an image, and the model for the image editor. This was done because an image knows how to store pixel data, and an image editor know how to store images. This way, we could easily break up and delegate the work to the specific classes for the suited tasks. As for how we designed these classes, we made the BasicEditor just implement the interface directly, and implement all of the methods. For the images, we decided an abstract base class would be good, so if we want to support more formats than PPM in the future, we could just extend the base class and implement saving and loading for those particular images. Another thing to note is that our images and pixels are all immutable, so every operation on pixels or images returns either a new pixel or a new image, to avoid mutation errors.

We chose in an extension to that design to have images be a board of IPixels, where each IPixel promises to return RGBA, though doesn't necessarily store those outright. This way, since RGBA is the most general form of RGB, we could convert between formats. We also don't guarantee that a board in an image will contain the same type of pixel, since the save method on images would know how to deal with that.

As for the controller design, we just implemented a controller with just one method start. Internally we represented the controller as a map of string commands to function objects, and told the editor to apply a function object to a particular image.

As for the commands, we just made a simple TriFunction command interface to represent all of our commands. We chose this to be an Image, Int, Int to Pixel so we could support operations that don't just require one pixel's data. 

As such, in the controller package:
ImageEditorController is the interface for the controller.
BasicEditorController is the basic version of the controller, which supports all of the commands that we made for this assignment (and takes in a view but doesn't yet do anything meaningful with it yey).

In the model package:
The ImageState model which represents an Image.
The IPixel interface which represents a general pixel.
The ImageEditorReadOnly and ImageEditor represent the read only and the editable version of the editor respectively, which would maintain the map of images internally and allow the user to interact with the model through the controller to load, save, remove, or edit images.
RGB and Grayscale pixels represent RGB and grayscale pixels.
AbstractBaseImage is the default abstract base class of the image state interface, and handles most generic operations like applying a filter to an image, constructing images, getting pixels at positions, and getting width/height. This is neat because now the only thing implementors need to do is make the save method, thus effectively opening up this design of images to extension to new formats which would know how to save themselves. 
ImagePPM is a concrete implementation of this, and all it needs to know how to do is return a new ImagePPM, and save itself to a file.
ImageUtil is the class provided to us in the starter code and is used to load PPMs from the disk.

In the operations package:
The only interesting thing here is the ImageRCToPixelTransformation, which is essentially a TriFunction which takes an Image and an R and C and produces a new Pixel. This exactly fits all of our needs for commands like flip or darken, because we can use other information from the image, other than just the particular pixel hole we are trying to fill in.
All of the commands follow a similar format, where they get a particular pixel, manipulate it, and return a new one. See Javadocs for detailed purpose statement for all of those, but they are all just the commands that were requested for this assignment. 

In the view package:
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

How to run:
Type the commands you want to execute into the terminal.

Example Script (enter these commands into the terminal one by one, making sure to have the res/graphics_lab.ppm image)
"load res/graphics_lab.ppm triangle"
"darken 100 triangle triangle2"
"vertical-flip triangle2 triangle3"
"red-component triangle3 triangle4
"save res/graphics_lab2.ppm triangle4"




