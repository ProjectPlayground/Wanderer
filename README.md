# Wanderer
This project started as a Single Screen App project completed for the "Udacity : User Interface [EU Scholarship]".

It started as a fake hostel business card and as I started creating the business card, I felt the need to add a "Book now" functionality to make it more useful somehow.

Thus, it is now a 2-Screen app and all of the features are described down below.

Note : I am still working on it but any tips, suggestion or pull requests are welcome! 

#Screen 1
![screenshot1](https://cloud.githubusercontent.com/assets/24596876/22485139/bab7ba3a-e804-11e6-9f08-9a7148200827.png)
![screenshot2](https://cloud.githubusercontent.com/assets/24596876/22485463/191fa730-e806-11e6-88a9-227037aa1f8f.png)
![screenshot3](https://cloud.githubusercontent.com/assets/24596876/22485467/1e7f5e28-e806-11e6-887e-4ff7c7fb5ece.png)


- ViewPager for the picture slide of the hostel
- Buttons that display/hide details about the hostel
- 3 intents in the contact us section (that will open : GooglemMaps, the phone dialer, and the mail app on the user's phone) 
- The "Book Now" button that leads to the second screen

#Screen 2

![screenshot4](https://cloud.githubusercontent.com/assets/24596876/22485503/3ddda3ec-e806-11e6-811a-3e29ed999c22.png)
![screenshot5](https://cloud.githubusercontent.com/assets/24596876/22485512/40fa5b42-e806-11e6-8572-a5d121605ceb.png)
![screenshot6](https://cloud.githubusercontent.com/assets/24596876/22485514/431722ca-e806-11e6-955a-f2d707175ce7.png)


- EditText fields, quantity pickers and spinner for the user to input the data related to his stay in the hostel
- Clickable Textfields that will calculate the price of the stay
- The "Send booking request" Button that open the mail app of the user (email intent) and prefill the object and message of the email according to the data given by the users.
- The "Home" button that leads back to the first screen

#Resources
- Image slide (ViewPager) https://discussions.udacity.com/t/4quotes-a-simple-slider-app/2131171
- How to swipe only one part of your layout http://stackoverflow.com/questions/24622942/how-to-swipe-only-a-portion-of-the-layout?noredirect=1&lq=1
- How to automatically swipe the pictures in ViewPager (set a timer) http://stackoverflow.com/questions/17610085/how-to-switch-automatically-between-viewpager-pages1
- How to set the visibility of your views (I used it to show/hide details of the hostel in the first screen) : http://stackoverflow.com/questions/3465841/how-to-change-visibility-of-layout-programaticly1
- How to make a multiple-screen app : http://stackoverflow.com/questions/10936042/how-to-open-layout-on-buttton-click-android1
- How to create a drop down list (spinner) : http://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list1
and http://www.mkyong.com/android/android-spinner-drop-down-list-example/1
- How to limit the type of characters accepted in EditText views : http://stackoverflow.com/questions/23212439/how-to-restrict-the-edittext-to-accept-only-alphanumeric-characters1





