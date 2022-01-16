# cleanersupply
![katalon](https://mma.prnewswire.com/media/1551615/Katalon_Logo.jpg?p=twitter)

###Prerequisites for katalon Installation
1.  [Java (JDK)](https://www.guru99.com/install-java.html)
2.  [Katalon studio](https://docs.katalon.com/katalon-studio/docs/getting-started.html)
3.  [Chrome WebDriver](https://www.automationtestinghub.com/download-chrome-driver/)


### How to start using this script
```
git clone https://github.com/TasneemKh1/cleanersupply.git
```

**To run the code**
1. File > open project (give the location for the cloned file)
2. Open test suit collection from test suites folder
```
https://github.com/TasneemKh1/cleanersupply/blob/master/Test%20Suites/Test%20Suite%20Collection.ts
```
3. Click execute button 

### Test cases covered by this app
Checkout Scenarios suite => 

###Scenario 1


https://user-images.githubusercontent.com/94033644/149650647-84c1f365-c5a8-4771-b408-64f2261486db.mp4



1- Navigate to https://www.cleanersupply.com/

2- Search for 'Plastic' term.

3- Select 'Packing Products' and 'Plastic Bags' options from category filter.

4- Select 'Green' from color group filter.

5- Navigte to the resulted product page.

6- Select 'X-Large' size, and 'Green' color.

7- Edit quantity value to be 5.

8- Add this item to the cart.

9- Select blue color, and large size for the same product.

10- add 3 items from this item.

11- Navigate to the cart.

12- Modify products quantities to be 4, and 4.

13- Click on 'Proceed To Checkout'

14- Select 'Checkut As Guest' and move to the next step.

15- Fill Shipping Address and payment method with fake data.

16- Navigate to next step (Review Order).

17- Observe the Checkout Review page content.

18- Close the browser

to do update on the test file for senario 1 follow the path to find all of the test cases
```
https://github.com/TasneemKh1/cleanersupply/blob/master/Test%20Cases/Checkout%20Folder/Search%20for%20product%20Test%20Case.tc
```

***********************************************

###Scenario 2


https://user-images.githubusercontent.com/94033644/149650492-821c7d44-6534-49b2-b725-da0f73ebab6f.mp4



1- Navigate to https://www.cleanersupply.com/

2- Hover on 'Tags & Forms' header items.

3- Select 'Computers & Registers' category.

4- From the manufacturer section, select the 'Casio' manufacturer and select 'SP1000' Model.

5- Enter the resulted product and 10 items from it to the cart.

6- Navigate to the cart.

7- Click on 'Proceed To Checkout'

8- Select 'Checkut As Guest' and move to the next step.

9- Fill Shipping Address and payment method with fake data.

10- Navigate to next step (Review Order).

11- Observe the Checkout Review page content.

12- Close the browser

to do update on the test file for senario 2 follow the path to find all of the test cases
```
https://github.com/TasneemKh1/cleanersupply/blob/master/Test%20Cases/Checkout%20Folder/Select%20Categorie%20Test%20Case.tc
```

***********************************************
###Scenario 3


https://user-images.githubusercontent.com/94033644/149650228-b1fef352-4dbd-4bc9-9767-f03d1b7fa919.mp4



1- Navigate to quick order page from the header.

2- Add any 5 products to the quick order list with random quantites between 5 and 50.

3- Add them to the cart

4- Navigate to the cart.

5- Click on 'Proceed To Checkout'

6- Select 'Checkut As Guest' and move to the next step.

7- Fill Shipping Address and payment method with fake data.

8- Navigate to next step (Review Order).

9- Observe the Checkout Review page content.

10- Close the browser

to do update on the test file for senario 3 follow the path to find all of the test cases
```
https://github.com/TasneemKh1/cleanersupply/blob/master/Test%20Cases/Checkout%20Folder/Quick%20order%20Test%20Case.tc
```
***********************************************

###Header Test Scenario: write test cases for the header section.


https://user-images.githubusercontent.com/94033644/149650439-e2cfbd1e-9ca5-4a1b-8f4a-db3f00778c87.mp4


test each link in header and verify the right corresponding page title ,URL and heading 
check img logo in header
search for keywords and stocks and check suggestions
check each item in nav 
view one of the nav dropdown


**********************************


https://user-images.githubusercontent.com/94033644/149650178-ac4d55f0-3e6f-480a-9b7f-9d2092c4c433.mp4


###Footer Test Scenario: write test cases for the footer section.
test each link in footer and verify the right corresponding page title ,URL and heading 
verify signing in for  EXCLUSIVE EMAIL 
check all link for social media 
check region dropdown
verify leave feedback modal is opened 


***********************************************
###Bugs found in the system


https://user-images.githubusercontent.com/94033644/149650192-bceea63c-7e89-442e-9367-efa4eba01b12.mp4


the product status changed from "in the stock" on the quick order page to out of stock in cart 

the Tax does not work as expected

filters count does not represent the total amount of products 
