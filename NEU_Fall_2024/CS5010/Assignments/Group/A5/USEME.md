A USEME file in the root submission folder (that contains src, test and res). The USEME file should 
summarize which script commands are supported by your application, examples of using them and 
conditions if any (e.g. X command should be typed before Y, etc.)

#load image
load ../res/images/mitra.jpg mitra

#compresses the image
#the percent of compression should be directly after the compress command
compress 100 mitra compress-mitra

#make a histogram of the image
histogram mitra histogram-mitra

#adjust the levels of the image
levels-adjust mitra levels-mitra

#color correct the image
color-correct mitra correct-mitra

#converts the image into sepia
#the split percentage of the image is added at the end of the command
sepia mitra sepia-mitra 50

#blurs the image
#the split percentage of the image is added at the end of the command
blur mitra blur-mitra 50

#sharpens the image
#the split percentage of the image is added at the end of the command
sharpen mitra sharp-mitra 50

#converts the image into grey-scale
#the split percentage of the image is added at the end of the command
grey-scale mitra grey-scale-mitra 50

#converts the image into red-component greyscale
red-component mitra red-mitra

#converts the image into green-component greyscale
green-component mitra green-mitra

#converts the image into blue-component greyscale
blue-component mitra blue-mitra

#combines red, green, blue grescale images into one color image
rgb-combine combo-mitra red-mitra green-mitra blue-mitra

#splits one color image into three red, green, blue greyscale images
rgb-split mitra rSplit-mitra gSplit-mitra bSplit-mitra

#flips the image horizontally
horizontal-flip mitra horizontal-mitra

#flips the image vertically
vertical-flip mitra horizontal-mitra

#brightens the image
#the percent of brightness should be directly after the brighten command
#to darken an image, the percent can be negative
brighten 50 mitra brighter-mitra
brighten -50 mitra darker-mitra

#save compress image
save ../res/images/compress-mitra.jpg compress-mitra

#save histogram image
save ../res/images/histogram-mitra.jpg histogram-mitra

#save color correct image
save ../res/images/correct-mitra.jpg correct-mitra

#save levels adjust image
save ../res/images/levels-mitra.jpg levels-mitra

#save image
save ../res/images/new-mitra.jpg mitra

#save sepia image
save ../res/images/sepia-mitra.jpg sepia-mitra

#save blur image
save ../res/images/blur-mitra.jpg blur-mitra

#save sharpen image
save ../res/images/sharp-mitra.jpg sharp-mitra

#save sharpen image
save ../res/images/grey-scale-mitra.jpg grey-scale-mitra

#save red image
save ../res/images/red-mitra.jpg red-mitra

#save green image
save ../res/images/green-mitra.jpg green-mitra

#save blue image
save ../res/images/blue-mitra.jpg blue-mitra

#save horizontal image
save ../res/images/horizontal-mitra.jpg horizontal-mitra

#save brighter image
save ../res/images/brighter-mitra.jpg brighter-mitra

#save darker image
save ../res/images/darker-mitra.jpg darker-mitra

#save combo image
save ../res/images/combo-mitra.jpg combo-mitra

#save split red image
save ../res/images/rSplit-mitra.jpg rSplit-mitra

#save split green image
save ../res/images/gSplit-mitra.jpg gSplit-mitra

#save split blue image
save ../res/images/bSplit-mitra.jpg bSplit-mitra