zSpecification Heading
=====================

This is an executable specification file. This file follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

To execute this specification, run
	gauge specs
Login
------------------------
* "10" saniye bekle
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "20" saniye bekle


Register
------------------------
* Wait "10" seconds
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "registerButton"
* "test" textini "firstNameInput" elemente yaz
* "testinium" textini "lastNameInput" elemente yaz
* Elementine tıkla "hbd1"
* Elementine tıkla "selectDate" 
* Elementine tıkla "hbd2"
* Elementine tıkla "selectMonth"
* Elementine tıkla "hbd3"
* Elementine tıkla "selectYear"
* "email" li elementi bul, temizle ve rasgele  email değerini yaz
* "1q2w3e4r5t" textini "registerPassword" elemente yaz
* "1q2w3e4r5t" textini "registerPasswordRepeat" elemente yaz
* Elementine tıkla "phoneNumberZoneCode"
* "2" saniye bekle
* Click to element "zoneCode"
* Click to element "phoneNumberTextField"
* Write random Int value to element "phoneNumberTextField"
* Elementine tıkla "acceptCheckBox"
* Elementine tıkla "registerNowButton"
//* "welcomeLabel" elementi "WELCOME" değerini içeriyor mu kontrol et

Place Bet
------------------------
* "15" saniye bekle
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "5" saniye bekle
* Elementine tıkla "betChoose"
* "5" saniye bekle
* Elementine tıkla "betAcceptButton"
* "5" saniye bekle

Place Combi Bet
------------------------
* "10" saniye bekle
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "5" saniye bekle
* Elementine tıkla "betChoose"
* "5" saniye bekle
* Elementine tıkla "combiBetItem"
* "5" saniye bekle
* Elementine tıkla "betAcceptButton"
* "5" saniye bekle

Change Password
------------------------
* "10" saniye bekle
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "5" saniye bekle
* "customerMenu" elementin üstünde bekle
* "1" saniye bekle
* Elementine tıkla "myAccount"
* Elementine tıkla "changePass"
* "password1" textini "currentPass" elemente yaz
* "Password2" textini "newPass" elemente yaz
* "Password2" textini "repeatNewPass" elemente yaz
* Elementine tıkla "saveButton"
* "customerMenu" elementin üstünde bekle
* Elementine tıkla "logOut"
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "Password2" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "customerMenu" elementin üstünde bekle
* "1" saniye bekle
* Elementine tıkla "myAccount"
* Elementine tıkla "changePass"
* "Password2" textini "currentPass" elemente yaz
* "password1" textini "newPass" elemente yaz
* "password1" textini "repeatNewPass" elemente yaz
* Elementine tıkla "saveButton"


## Login test
tags:login
* Open Test environment
* Login Account

## Register new
tags: Register
* Open Test environment
* Register Page Open
* Register New Account

## Place new a Bet
tags: Single Bet
* Open Test environment
* Login Account
* Place Single Bet

## Place new Combi Bet
tags: Combi bet
* Open Test environment
* Login Account
* Place a Combi Bet

## Open My Bets
tags: MY account
* Open Test environment
* Login Account
* Open My account
* My Bets

## Transactions Page
tags: Transaction
* Open Test environment
* Login Account
* Open My account
* Transactions Bookings
* Transactions Deposit
* Transactions Withdrawal
* My Accounts Account Settings Personal Details

## Upload Documents
* Open Test environment
* Login Account
* Open My account
* My Account Upload Documents

## Change Password V2
* Open Test environment
* Login Account
* Open My account
* New Password
* Logout
* Login With New Password
* Open My account
* Old Password

## Change Default Stake
* Open Test environment
* Login Account
* Open My account
* Open Bet Settings
* Enter New Default Stake
* Open Main Page
* Wait "10" seconds
* Open My account
* Open Bet Settings
* Enter Old Default Stake
* Wait "10" seconds

## Change Odd Value Changes
* Open Test environment
* Login Account
* Open My account
* Open Bet Settings
* Change Odd Value Changes

## Make Withdrawal With APS Method
* Open Test environment
* Login Account
* Open My account
* Transactions Withdrawal
* Choose to Payment Method with "apsWithdrawalMethods"
* Genarete Random Transaction Value and Enter The Value
* Select the Bank Type and Enter the Account Number
* Select the Identity Type and Enter the Account Number
* Continue To Transaction
* Compare the entered withdrawal amount "randomNumber" to amount "requestedAmountInWithdrawalPage" which inside the transactions info by converted text "requestedWithdraw"
//* Calculate the balance
* Get Transaction Number In Withdrawal Page
* Complete the transaction
* Compare the transactions numbers

## Make Withdrawal With Monnet Bank Out Method
* Open Test environment
* Login Account to Equador country
* Open My account
* Transactions Withdrawal
* Choose to Payment Method with "monnetBankOutWithdrawalMethods"
* Genarete Random Transaction Value and Enter The Value For Monnet WithDrawal
* Select the Bank Type and Enter the Account Number
* Select the Identity Type and Enter the Identity Number For Monnet
* Continue To Transaction
* Compare the entered withdrawal amount "randomNumber" to amount "requestedAmountInWithdrawalPage" which inside the transactions info by converted text "requestedWithdraw"
* Get Transaction Number In Withdrawal Page
* Complete the transaction
* Compare the transactions numbers

## Make Withdrawal With Apco Method
* Open Test environment
* Login Account
* Open My account
* Transactions Withdrawal
* Choose to Payment Method with "apcoWithdrawalMethod"
* Genarete Random Transaction Value and Enter The Value
* Select the Card and Enter the Missing Number
* Select the Card Expiration Date For Month
* Select the Card Expiration Date For Year
* Continue To Transaction
* Compare the entered withdrawal amount "randomNumber" to amount "requestedAmountInWithdrawalPage" which inside the transactions info by converted text "requestedWithdraw"
//* Calculate the balance
* Get Transaction Number In Withdrawal Page
* Complete the transaction
* Compare the transactions numbers

## Make Withdrawal With Accent Out Method
* Open Test environment
* Login Account
* Open My account
* Transactions Withdrawal
* Choose to Payment Method with "accentOutWithdrawal"
* Genarete Random Transaction Value and Enter The Value
* Select the Card and Enter the Missing Number
* Select the Card Expiration Date For Month
* Select the Card Expiration Date For Year
* Continue To Transaction
* Compare the entered withdrawal amount "randomNumber" to amount "requestedAmountInWithdrawalPage" which inside the transactions info by converted text "requestedWithdraw"
//* Calculate the balance
* Get Transaction Number In Withdrawal Page
* Complete the transaction
* Compare the transactions numbers

## Make Deposite With Accent In Method
tags:makeDepositWithAccentInMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Choose to Payment Method with "depositAccentInMethod"
* Genarete Random Deposit Value and Enter that value
* Continue To Transaction For Deposit
* Click on Perform Payment
* Enter Credit Card İnformation with "4051 8856 0044 6623", "Test Test" and "123"
//* Enter Digipass Code with "123"
//* Verify the deposit information and continue the main site
//* Go to Booking Page

## Make Deposite With Apco Method
tags:makeDepositWithApcoMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Choose to Payment Method with "depositApcoMethod"
* Genarete Random Deposit Value and Enter that value
* Continue To Transaction For Deposit
* Click on Perform Payment
* Enter Credit Card İnformation with "4051 8856 0044 6623", "Test Test" and "123" apco
////* Verify the deposit information and continue the main site
////* Go to Booking Page


## Make Deposite With Monnet Bank Transfer Method
tags:makeDepositWithMonnetBankTransferMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Get list of payment method for selection "getListOfPaymentMethods"
* Select Payment Method with "depositMonnetBank"
//* Click on amount field "depositAmount"
* Genarete Random Deposit Value and Enter that value
* Select the Identity Type and Enter the Identity Number
* Continue To Transaction For Deposit
* Click on Perform Payment
//* Compare the deposit amount "randomNumber" to the value in payment method "paymentValue" by replaced text "depositAmountInThirdPartForMonnetBankMethod"
* Select the bank option for Monnet Bank Method



## Make Deposite With Monnet Card Method
tags:makeDepositWithMonnetMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Choose to Payment Method with "monnetCardMethod"
* Genarete Random Deposit Value and Enter that value
* Select the Identity Type and Enter the Identity Number
* Continue To Transaction For Deposit
* Click on Perform Payment
* Select to the credit option for deposit method
* Enter Credit Card İnformation with "4051 8856 0044 6623", "123" and "1224" monnet
//* Select to the installment option
* Enter Rut and Clave info at Welcome of TransBank page
* Accept the option on TransBank page to be done the payment

## Make Deposite With Monnet Debit Method
tags:makeDepositWithMonnetDebitMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Choose to Payment Method with "monnetCardMethod"
* Genarete Random Deposit Value and Enter that value
* Select the Identity Type and Enter the Identity Number
* Continue To Transaction For Deposit
* Click on Perform Payment
//* Compare the deposit amount "randomNumber" to the value in payment method "paymentValue" by replaced text "depositAmountInThirdPartForMonnetDebitMethod"
* Select to the "debitOptionForMonnet" option for deposit method
* Select the bank option and enter bank card info "4051 8856 0044 6623" for debit
* Enter Rut and Clave info at Welcome of TransBank page
* Accept the option on TransBank page to be done the payment

## Make Deposite With APS Bank Transfer Method
tags:makeDepositWithAPSBankTransferMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Choose to Payment Method with "depositAPSBank"
* Genarete Random Deposit Value and Enter that value
* Continue To Transaction For Deposit
* Click on Perform Payment
* Enter Bank Account Information "111111111" for APS
* Click Back Trade And Redirect to website

## Make Deposite With APS Card Method
tags:makeDepositWithAPSCardMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Choose to Payment Method with "depositAPSCard"
* Genarete Random Deposit Value and Enter that value
* Continue To Transaction For Deposit
* Click on Perform Payment
* Enter Bank Account Information "111111111" for APS
* Select to the credit option for deposit method
* Enter Credit Card İnformation with "4051 8856 0044 6623", "123" and "1224" APS
* Enter Rut and Clave info at Welcome of TransBank page
* Accept the option on TransBank page to be done the payment


## Make Deposite With APS Debit Method
tags:makeDepositWithAPSDebitMethod
* Open Test environment
* Login Account
* Open My account
* Transactions Deposit
* Choose to Payment Method with "depositAPSCard"
* Genarete Random Deposit Value and Enter that value
* Continue To Transaction For Deposit
* Click on Perform Payment
* Enter Bank Account Information "111111111" for APS
* Select to the "debitOptionForMonnet" option for deposit method
* Select the bank option and enter bank card info "4051 8856 0044 6623" for APS debit
* Enter Rut and Clave info at Welcome of TransBank page
* Accept the option on TransBank page to be done the payment



