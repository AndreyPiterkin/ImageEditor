USEME:

<h2>Supported Commands:</h2>

<h3>Load</h3>
``load path name-to-save-in-editor``

<p>This command can be used to load an image from the given path into the editor.

This command will be the first one that should be called, as upon launch the editor
won't contain any images.

Both arguments must be strings, and the name to save as in the editor will be the name
used to refer to the image from now on (and can't contain spaces). 

Valid paths should include
the file extension of the image you want to load. Supported extensions: "bmp", "ppm", "jpg", "png"</p>

Examples:

``load <path>/cat.bmp cat``

``load <path>/cat.ppm cat2``

``load <path>/cat.jpg cat3``

``load <path>/cat.png cat4``

<h3>Save</h3>
``save name path``
<p>This command can be used to save an image from the editor to the given path.

Both arguments must be strings, and the name must be the name of an image loaded in the editor.

Valid paths should include the file extension of the image you want to save. Supported extensions: "bmp", "ppm", "jpg", "png"</p>

Examples:

``save cat <path>/cat.bmp ``

``save cat2 <path>/cat.ppm ``

``save cat3 <path>/cat.jpg ``

``save cat4 <path>/cat.png ``

<h3>Blur</h3>
``blur name name-to-save-in-editor``
<p>This command can be used to apply a Gaussian blur to an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``blur cat cat_blurred``


<h3>Sharpen</h3>
``sharpen name name-to-save-in-editor``
<p>This command can be used to apply a sharpening filter to an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``sharpen cat cat_sharpened``

<h3>Grayscale</h3>
``grayscale name name-to-save-in-editor``
<p>This command can be used to apply a grayscale kernel filter to an image in the editor.
This uses the same formula as luma, but computes via kernel.
The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``grayscale cat cat_grayscale``

<h3>Sepia</h3>
``sepia name name-to-save-in-editor``
<p>This command can be used to apply a sepia kernel filter to an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``sepia cat cat_sepia``

<h3>Red Component</h3>
``red-component name name-to-save-in-editor``
<p>This command can be used to turn an image grayscale using the red component of an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``red-component cat cat_red_component``

``red-component cat2 cat2_red_component``

<h3>Green Component</h3>
``green-component name name-to-save-in-editor``

<p>This command can be used to turn an image grayscale using the green component of an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``green-component cat cat_green_component``

``green-component cat2 cat2_green_component``

<h3>Blue Component</h3>
``blue-component name name-to-save-in-editor``
<p>This command can be used to turn an image grayscale using the blue component of an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``blue-component cat cat_blue_component``

``blue-component cat2 cat2_blue_component``

<h3>Intensity Component</h3>
``intensity-component name name-to-save-in-editor``

<p>This command can be used to turn an image grayscale (using the intensity of each pixel) of an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``intensity-component cat cat_intensity_component``

``intensity-component cat2 cat2_intensity_component``

<h3>Luma Component</h3>
``luma-component name name-to-save-in-editor``
<p>This command can be used to turn an image grayscale (using the luma of each pixel) of an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``luma-component cat cat_luma_component``

``luma-component cat2 cat2_luma_component``

<h3>Value Component</h3>
``value-component name name-to-save-in-editor``
<p>This command can be used to turn an image grayscale (using the value of each pixel) of an image in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``value-component cat cat_value_component``

``value-component cat2 cat2_value_component``

<h3>Brighten</h3>
``brighten amount name name-to-save-in-editor``
<p>This command can be used to brighten an image in the editor.

The first argument after brighten must be the amount to brighten by (an integer),
followed by the name of the image in the editor and the name to save as.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the last argument</p>

Examples:

``brighten 10 cat cat_brightened``

``brighten 20 cat2 cat2_brightened_more``

<h3>Darken</h3>
``darken amount name name-to-save-in-editor``
<p>This command can be used to darken an image in the editor.

The first argument after darken must be the amount to darken by (an integer),
followed by the name of the image in the editor and the name to save as.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the last argument</p>

Examples:

``darken 10 cat cat_darkened``

``darken 20 cat2 cat2_darkened_more``

<h3>Flip Horizontal</h3>
``flip-horizontal name name-to-save-in-editor``
<p>This command can be used to flip an image horizontally in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``flip-horizontal cat cat_flipped``

``flip-horizontal cat2 cat2_flipped``

<h3>Flip Vertical</h3>
``flip-vertical name name-to-save-in-editor``

<p>This command can be used to flip an image vertically in the editor.

The name must be the name of an image in the editor. The new name is the new name to save as, and must always
be the third argument</p>

Examples:

``flip-vertical cat cat_flipped``

``flip-vertical cat2 cat2_flipped``

