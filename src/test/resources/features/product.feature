Feature: product data table practice


  # practice List <Map <String, String>>
  # practice List <List<String>>
  # Map<String, List<String>>

  @listOfMap
  Scenario: verify each product price
  # practice List<Map<String, String>>
    Given User is on the HomePage
    Then User should be able to see expected prices in the following products
      | Category | Product           | expectedPrice |
      | Phones   | Samsung galaxy s6 | 360           |
      | Phones   | Nokia lumia 1520  | 820           |
      | Phones   | Nexus 6           | 650           |
      | Laptops  | Sony vaio i5      | 790           |
      | Laptops  | Sony vaio i7      | 790           |
      | Laptops  | MacBook air       | 700           |
      | Monitors | Apple monitor 24  | 400           |
      | Monitors | ASUS Full HD      | 230           |

        #  List Element 1 (Map):
      #  {Category=Phones, Product=Samsung galaxy s6, expectedPrice=360}
      #  List Element 2 (Map):
      #  {Category=Phones, Product=Nokia lumia 1520, expectedPrice=820}
      #  List Element 3 (Map):
      #  {Category=Phones, Product=Nexus 6, expectedPrice=650}
      #  List Element 4 (Map):
      #  {Category=Laptops, Product=Sony vaio i5, expectedPrice=790}
      #  List Element 5 (Map):
      #  {Category=Laptops, Product=Sony vaio i7, expectedPrice=790}
      #  List Element 6 (Map):
      #  {Category=Laptops, Product=MacBook air, expectedPrice=700}
      #  List Element 7 (Map):
      #  {Category=Monitors, Product=Apple monitor 24, expectedPrice=400}
      #  List Element 8 (Map):
      #  {Category=Monitors, Product=ASUS Full HD, expectedPrice=230}



@listOfLists
  # practice List <List<String>>
Scenario: verify each product price ListOfList
  Given User is on the HomePage
  Then User should be able to see expected prices in the following products with listOfLists
    | Phones   | Samsung galaxy s6 | 360 |
    | Phones   | Nokia lumia 1520  | 820 |
    | Phones   | Nexus 6           | 650 |
    | Laptops  | Sony vaio i5      | 790 |
    | Laptops  | Sony vaio i7      | 790 |
    | Laptops  | MacBook air       | 700 |
    | Monitors | Apple monitor 24  | 400 |
    | Monitors | ASUS Full HD      | 230 |


   #  List Element 1 (<List<String>): [ Phones, Samsung galaxy s6,  360 ]
      #  List Element 2 (<List<String>):  [Phones, Nokia lumia 1520,820]
      #  List Element 3 ((<List<String>)):
      #  List Element 4......
      #  List Element 5.....
  #.....
      #  List Element 8.....



  @mapList
  # Map<String, List<String>>
  Scenario: verify student names
    Then user should be able to see the names
      | Group 2 | nadir | feyruz | Jane   |
      | Group 3 | vika  | suidum | savlat |

 # List Element 1(List<String>): [Groop2, nadir, feyruz, jane]
 # List Element 2(List<String>): [Groop3, vika suidum | savlat]


  @mapListProduct
     # Map<String, List<String>>
  Scenario: verify each product price map
    Given user is on Home page page
    Then User should be able to see expected prices in the following products with map

      | Phones   | Samsung galaxy s6  - 360 | Nokia lumia 1520  - 820 | Nexus 6 - 650      |
      | Laptops  | Sony vaio i5 - 790       | Sony vaio i7 - 790      | MacBook air - 700 |
      | Monitors | Apple monitor 24 - 400   | ASUS Full HD - 230      |                   |









