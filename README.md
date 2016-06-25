# SimpleTwitterClient
This is a simple twitter client that user can login with his twitter account and see his followers and last 10 tweets of them. 

#### Basic useful feature list:

   **1. Login using twitter account**.
  * save user details (Fullname,handle,email,profile image url,banner image url,session) in device settings storage to not open the login screen page again. it will open the second screen.
 
  **2. User followers list**.
   * It display logged-in user followers in a list. The list contains profile image, full name,handle,and bio(if exists).
 * Followers API response had been cashed for offline use.
 * There is swipe to refresh and load more features in followers list.


  **3. Followers information screen**.
 * Display follower profile Image and background (if not; add any default image).
 * Display last 10 tweets in a list.

 **4. Multi-Language.** 
 * This application support multi-language (UI and Text) depends on user device language  **Example:** if device language is arabic you can find that arbic interface (RTL), else you can find english interface (LTR).

-----------
below i will explain every class with it's logic and what is used with it.

## 1. LoginActivity.
* This activity contains twitter login button which open Fabric Lib login screen and mobile user can login using his twitter account and then this screen should be dismissed after login successfully.

### What we used in this activity

1. Fabric library (3rd party lib) that use twitter comsumer key and consumer secret which i created in my twitter developer account and returned user details (name , handle , profile image ) and acces token in case of successfully login.
2. User details and access tokens keys(token key and secret key) that was returned from successfuly login should be saved in device shared preferences to use it in next using without login again.

## 2. Followers Activity.
* This activity displays loged-in twitter user account and  list of his followers 

### What we used in this activity

1. Use cashed user id and acess token key to get followers list from [followers/list.json](https://dev.twitter.com/rest/reference/get/followers/list) by using twitter API client. 
2. Use **GSON** 3rd party API to parse returned followers list json string.
3. Use **RecyclerView,RecyclerView.Adapter and CardView** to display List of followers users.
4. Use **Snackbar** to display connection error messages.
5. Use **picasso** 3rd party API to load and cash user/followers images.
6. Use SwipeToRefreshLayout to reload/refresh followersList API.
7. Use **EndlessRecyclerViewScrollListener** that extends **RecyclerView.OnScrollListener** to implement loadmore feature.
8. We added logout option in toolbar taht clear user saved data from preferences.
9. We saved user followers list inside internal storage files.

## 3. Followers Information (Follower User Profile Activity). 
* This screen displays selected follower user profile (name , profile image , banner iamge and list of latest 10 twiites) 

### What we used in this activity

1. Use cashed user id and acess token key to get followers list from [statuses/user_timeline.json](https://dev.twitter.com/rest/reference/get/statuses/user_timeline) by using twitter API client. 
2. Use **Manual Parser** to parse returned Tweets list json string.
3. Use **RecyclerView,RecyclerView.Adapter and CardView** to display List of user Tweets.
4. Use **Snackbar** to display connection error messages.
5. Use **picasso** 3rd party API to load and cash user/followers images.
6. Use **CoordinatorLayout and CollapsingToolbarLayout** to display sticky header for background image.  

## 4. General features.
* We support multilanguage En/Ar but depends on user device language. 
* * We created 2 string files one for arabic and 2nd for english.
* * We created 2 layouts folder one for arabic layout and the second for english.
* * We used Material design concept.
