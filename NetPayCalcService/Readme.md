# Getting Started

## Reference Documentation

### 1. Executing the service

Please follow below steps for executing this project in windows operating system.

Step 1: Please download "NetPayCalcService-0.0.1-SNAPSHOT.jar" from GitHub and save it under "D:\" path.

Step 2: Open Command Prompt and execute below mentioned script.

	java -jar D:\NetPayCalcService-0.0.1-SNAPSHOT.jar

Step 3: After successful start up, you will see below message under Command Prompt Console.

	Started NetPayCalcServiceApplication in XX seconds

### 2. Testing the service

Step 1: Please hit below URL from any browser.

	URI : http://localhost:8080/calculateNetBillAmt/{billAmt}/{customerType}/{purchaseType}
	
Please change below values: 
	
	{billAmt} : This parameter will hold the initial bill amount before applying the discount. Please follow format like 10000.00
	
	{customerType}: This parameter will hold the customer Type value. The possible values are Employee , Affiliate, LoyalCustomer , Others
	
	{purchaseType}: This parameter will hold the purchase type value. The possible values are Groceries, Others.
	
Below example URL:
	
	http://localhost:8080/calculateNetBillAmt/1000/LoyalCustomer/Groceries
	
Sample Response:

	{"finalBillAmt":1000.0,"errorCode":"000","errorDesc":"Success"}
			
I have also prepared Swagger API documentation for this service. Please hit below URL to view complete swagger API documentation as well.
	
	http://localhost:8080/swagger-ui.html
	
### 3. Importing the project in Eclipse IDE

Please follow below steps for importing this project under Eclipse IDE.

Step 1: 


