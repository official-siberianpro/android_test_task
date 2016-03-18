# android_test_task
Android Test Task

Create one-screen app. On the screen it should be only a list that contain groups. Groups should 
be able to click in order to roll out and show items included in the group.
App should load data from data.json file, contained as the asset.
App should not show empty groups.
Long Press gesture on a group of the even id should cause a spin of the list regarding to the 
middle of the screen, clockwise, 360 degrees in a second.
In groups of odd id, a spin should be in an opposite direction.
Long Press gesture made on an item should show a dialog with a group name as a title and item 
name as a content. Dialog should contain "OK" button, that hides it.
Layout:
Element of the list - group:
- hight 48dp
- background #FFFFFF, when pushed: #BABABA
- text hight 16dp, color:  #333333, interval from the left edge: 36dp, interval from the right edge: 
16dp, font: atteched Roboto-Light font; single line, aligned: left and center vertcial
List's element - item:
- hight: 48dp
- background #EEEEEE; when pushed: #BABABA
- text hight 16dp, color #333333, interval from the left edge: 36dp, interval from the right edge: 
16dp, font: atteched Roboto-Light font; single line, aligned: left and center vertcial
List's elements parameters, including widgets to display the text should be declared in app styles, 
not in the file with layout.
Android version is optional.
