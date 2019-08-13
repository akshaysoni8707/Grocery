import java.util.Arrays;
import java.util.Scanner;

class Bill extends inventory {
    //enum work {bill,additem,updateitem,search,stop,quit}
    private Bill[] bill_item = new Bill[2];
    private int productId;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private int counter1;
    private String discountMessage;

    private Bill(inventory ob, int quantity) {
        this.productId = ob.product_id;
        this.productName = ob.product_name;
        this.productQuantity = quantity;
        this.productPrice = productQuantity * ob.product_price;
    }

    private Bill() {
    }

    public static void main(String[] args) {
        Bill a = new Bill();
        a.billGenerator();
    }

    private void billReset() {
        if (bill_item[0] != null) {
            bill_item = null;
            bill_item = new Bill[2];
            counter1 = 0;
        }
    }

    private double discount(double total_) {
        try {
            if (total_ >= 500) {
                discountMessage = "you are illigible for dicount of 10 % \n\t\t\t\t\t\t\t\t\t\t\tdiscount :-" + (total_ / 10);
                total_ = total_ - (total_ / 10);
            } else {
                throw new ArithmeticException("you are not illigible for discount");
            }
        } catch (Exception e) {
            discountMessage = e.getMessage();
        }
        return total_;
    }

    private void addBill(int giveId, int quantity, inventory ob) {
        boolean checkitem = false;
        int j;
        Scanner response = new Scanner(System.in);
        for (j = 0; j < counter1; j++) {
            if (giveId == bill_item[j].productId) {
                checkitem = true;
                break;
            }
        }
        if (checkitem) {
            for (int i = 0; i < ob.counter; i++) {
                if (ob.inventoryItem[i].product_id == giveId) {
                    if (ob.inventoryItem[i].product_quantity == 0) {
                        System.out.println("\nWe are out of stock for " + ob.inventoryItem[i].product_name);
                        break;
                    } else if (ob.inventoryItem[i].product_quantity - quantity < 0) {
                        System.out.println("\nWe have only " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name + " left");
                        System.out.println("Do you want all " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name);
                        System.out.println("\n if yes type Y       if no type N");
                        try {
                            String ans = response.nextLine();
                            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")) {
                                if (ans.equalsIgnoreCase("y")) {
                                    bill_item[j].productQuantity += ob.inventoryItem[i].product_quantity;
                                    bill_item[j].productPrice = bill_item[j].productQuantity * ob.inventoryItem[i].product_price;
                                    ob.inventoryItem[i].product_quantity = 0;
                                    System.out.println("\n" + bill_item[j].productId + " " + bill_item[j].productName + " " + bill_item[j].productQuantity + " " + bill_item[j].productPrice);
                                    System.out.println(ob.inventoryItem[i].product_id + "  " + ob.inventoryItem[i].product_name + "  " + ob.inventoryItem[i].product_quantity + "  " + ob.inventoryItem[i].product_price);
                                } else {
                                    System.out.println("\nsorry for inconvinience due to product shortage");
                                }
                            } else {
                                throw new ArithmeticException("\nInvalid Response");
                            }
                        } catch (Exception e) {
                            System.out.println("\nerror : " + e.getMessage());
                        }
                        break;
                    } else {
                        bill_item[j].productQuantity += quantity;
                        bill_item[j].productPrice = bill_item[j].productQuantity * ob.inventoryItem[i].product_price;
                        ob.inventoryItem[i].product_quantity -= quantity;
                        System.out.println("\n" + bill_item[j].productId + " " + bill_item[j].productName + " " + bill_item[j].productQuantity + " " + bill_item[j].productPrice);
                        System.out.println(ob.inventoryItem[i].product_id + "  " + ob.inventoryItem[i].product_name + "  " + ob.inventoryItem[i].product_quantity + "  " + ob.inventoryItem[i].product_price);
                        break;
                    }
                }
            }
        } else {
            if (counter1 >= 2) {
                this.bill_item = Arrays.copyOf(bill_item, counter1 + 1);
            }
            for (int i = 0; i < ob.counter; i++) {
                if (ob.inventoryItem[i].product_id == giveId) {
                    if (ob.inventoryItem[i].product_quantity == 0) {
                        System.out.println("\nWe are out of stock for " + ob.inventoryItem[i].product_name);
                        break;
                    } else if (ob.inventoryItem[i].product_id == giveId && ob.inventoryItem[i].product_quantity - quantity < 0) {
                        System.out.println("\nWe have only " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name + " left");
                        System.out.println("Do you want all " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name);
                        System.out.println("\n if yes type Y       if no type N");
                        try {
                            String ans = response.nextLine();
                            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")) {
                                if (ans.equalsIgnoreCase("y")) {
                                    bill_item[counter1++] = new Bill(ob.inventoryItem[i], ob.inventoryItem[i].product_quantity);
                                    ob.inventoryItem[i].product_quantity = 0;
                                    System.out.println("\n" + bill_item[counter1 - 1].productId + " " + bill_item[counter1 - 1].productName + " " + bill_item[counter1 - 1].productQuantity + " " + bill_item[counter1 - 1].productPrice);
                                    System.out.println(ob.inventoryItem[i].product_id + "  " + ob.inventoryItem[i].product_name + "  " + ob.inventoryItem[i].product_quantity + "  " + ob.inventoryItem[i].product_price);
                                } else {
                                    System.out.println("\nsorry for inconvinience due to product shortage");
                                }
                            } else {
                                throw new ArithmeticException("\nInvalid Response");
                            }
                        } catch (Exception e) {
                            System.out.println("\nerror : " + e.getMessage());
                        }
                        break;
                    } else {
                        bill_item[counter1++] = new Bill(ob.inventoryItem[i], quantity);
                        ob.inventoryItem[i].product_quantity -= quantity;
                        System.out.println("\n" + bill_item[counter1 - 1].productId + " " + bill_item[counter1 - 1].productName + " " + bill_item[counter1 - 1].productQuantity + " " + bill_item[counter1 - 1].productPrice);
                        break;
                    }
                } else if (i == ob.counter - 1) {
                    System.out.println("\n Wrong Id of Product");
                    break;
                }
            }
        }
    }

    private void addBill(String giveName, int quantity, inventory ob) {
        boolean checkitem = false;
        Scanner response = new Scanner(System.in);
        int j;
        for (j = 0; j < counter1; j++) {
            if (giveName.equalsIgnoreCase(bill_item[j].productName)) {
                checkitem = true;
                break;
            }
        }
        if (checkitem) {
            for (int i = 0; i < ob.counter; i++) {
                if (ob.inventoryItem[i].product_name.equalsIgnoreCase(giveName)) {
                    if (ob.inventoryItem[i].product_quantity == 0) {
                        System.out.println("\n We are out of stock for " + ob.inventoryItem[i].product_name);
                        break;
                    } else if (ob.inventoryItem[i].product_quantity - quantity < 0) {
                        System.out.println("\nWe have only " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name + " left");
                        System.out.println("Do you want all " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name);
                        System.out.println("\n if yes type Y       if no type N");
                        try {
                            String ans = response.nextLine();
                            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")) {
                                if (ans.equalsIgnoreCase("y")) {
                                    bill_item[j].productQuantity += ob.inventoryItem[i].product_quantity;
                                    bill_item[j].productPrice = bill_item[j].productQuantity * ob.inventoryItem[i].product_price;
                                    ob.inventoryItem[i].product_quantity = 0;
                                    System.out.println("\n" + bill_item[j].productId + " " + bill_item[j].productName + " " + bill_item[j].productQuantity + " " + bill_item[j].productPrice);
                                    System.out.println(ob.inventoryItem[i].product_id + "  " + ob.inventoryItem[i].product_name + "  " + ob.inventoryItem[i].product_quantity + "  " + ob.inventoryItem[i].product_price);
                                } else {
                                    System.out.println("\nsorry for inconvinience due to product shortage");
                                }
                            } else {
                                throw new ArithmeticException("\nInvalid Response");
                            }
                        } catch (Exception e) {
                            System.out.println("\nerror : " + e.getMessage());
                        }
                        break;
                    } else {
                        bill_item[j].productQuantity += quantity;
                        bill_item[j].productPrice = bill_item[j].productQuantity * ob.inventoryItem[i].product_price;
                        ob.inventoryItem[i].product_quantity -= quantity;
                        System.out.println("\n" + bill_item[j].productId + " " + bill_item[j].productName + " " + bill_item[j].productQuantity + " " + bill_item[j].productPrice);
                        System.out.println(ob.inventoryItem[i].product_id + "  " + ob.inventoryItem[i].product_name + "  " + ob.inventoryItem[i].product_quantity + "  " + ob.inventoryItem[i].product_price);
                        break;
                    }
                }
            }
        } else {
            if (counter1 >= 2) {
                this.bill_item = Arrays.copyOf(bill_item, counter1 + 1);
            }
            for (int i = 0; i < ob.counter; i++) {
                if (ob.inventoryItem[i].product_name.equals(giveName)) {
                    if (ob.inventoryItem[i].product_quantity == 0) {
                        System.out.println("\n We are out of stock for " + ob.inventoryItem[i].product_name);
                        break;
                    } else if (ob.inventoryItem[i].product_quantity - quantity < 0) {
                        System.out.println("\nWe have only " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name + " left");
                        System.out.println("Do you want all " + ob.inventoryItem[i].product_quantity + " of " + ob.inventoryItem[i].product_name);
                        System.out.println("\n if yes type Y       if no type N");
                        try {
                            String ans = response.nextLine();
                            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")) {
                                if (ans.equalsIgnoreCase("y")) {
                                    bill_item[counter1++] = new Bill(ob.inventoryItem[i], ob.inventoryItem[i].product_quantity);
                                    ob.inventoryItem[i].product_quantity = 0;
                                    System.out.println("\n" + bill_item[counter1 - 1].productId + " " + bill_item[counter1 - 1].productName + " " + bill_item[counter1 - 1].productQuantity + " " + bill_item[counter1 - 1].productPrice);
                                    System.out.println(ob.inventoryItem[i].product_id + "  " + ob.inventoryItem[i].product_name + "  " + ob.inventoryItem[i].product_quantity + "  " + ob.inventoryItem[i].product_price);
                                } else {
                                    System.out.println("\nsorry for inconvinience due to product shortage");
                                }
                            } else {
                                throw new ArithmeticException("\nInvalid Response");
                            }
                        } catch (Exception e) {
                            System.out.println("\nerror : " + e.getMessage());
                        }
                        break;
                    } else {
                        bill_item[counter1++] = new Bill(ob.inventoryItem[i], quantity);
                        ob.inventoryItem[i].product_quantity -= quantity;
                        System.out.println(bill_item[counter1 - 1].productId + " " + bill_item[counter1 - 1].productName + " " + bill_item[counter1 - 1].productQuantity + " " + bill_item[counter1 - 1].productPrice);
                        break;
                    }
                } else if (i == ob.counter - 1) {
                    System.out.println("\n Wrong Name of Product");
                    break;
                }
            }
        }
    }

    private void billGenerator() {
        //  long start1 = System.currentTimeMillis();
        final inventory myInventory = new inventory();
        Bill.customers myCustomer = new Bill.customers();
        float total = 0;
        Scanner scan = new Scanner(System.in);
        myInventory.addItems(1, "orange", 4, 24);
        //      myInventory.addItems(2, "apple", 3, 25.5);
        //myInventory.addItems(5, "apple", 2, 25);
        //     myInventory.addItems(3, "mango", 9, 26);
        //      myInventory.addItems(4, "grapes", 5, 27);
       /* for (int i = 5; i < 10000; i++) {
            myInventory.addItems(i, "mango" + i, 9, 26);
        }
        //  System.out.println(myInventory.toString());
        //addBillLoop(myInventory);
        addBill(1, 4, myInventory);
        addBill(9999, 3, myInventory);
        addBill("apple", 2, myInventory);
        addBill("grapeS", 3, myInventory);
        System.out.println(billDisplay(total));

        System.out.println("\n Inventory after bill generated....\n");
        // System.out.println(myInventory.toString());

        myInventory.updateItems(2, 5);
        myInventory.updateItems(9997, 9);
        myInventory.updateItems("ORange", 3);
        myInventory.updateItems("MANGO9999", 5146);
        myInventory.updateItems(9980, 0, 34);
        myInventory.updateItems(9905, 1, 57);
        myInventory.updateItems("mAngo", 1, 60);
        myInventory.updateItems("mAngo9998", 5, 57);

        System.out.println("\nLets check if inventory is updated...\n");
        //   System.out.println(myInventory.toString());

        long end1 = System.currentTimeMillis();
        System.out.println(" time taken for program to run :" + (end1 - start1));
        long start = System.currentTimeMillis();

        System.out.println("\n Searching items.");
        myInventory.searchItems("mango9905");
        myInventory.searchItems("mango9998");
        myInventory.searchItems(9980);
        myInventory.searchItems(9999);*/

        //  long end = System.currentTimeMillis();
        // System.out.println(" time taken for program to run :" + (end - start));

        String check;
        String data;
        do {
            System.out.println("\n\n\t  What do you want to do:\n" +
                    "Bill : To add item to your bill\nAdditem : To add new item to inventory\n" +
                    "Updateitem : To update an exsiting item in inventory\n" +
                    "Search : to search any particular item from inventory\n" +
                    "allcustomer : displays all customers with their bills\n" +
                    "searchcustomer : search a particular customer display all his/her bills\n" +
                    "Stop : To stop current work\n" +
                    "Quit : To quit the program\n");
            scan.reset();
            check = scan.nextLine();
            switch (check.toLowerCase()) {
                case "bill":
                    do {
                        System.out.println("\n\nEnter ID or NAME of the product:\nTo generate bill type STOP");
                        scan.reset();
                        data = scan.nextLine();
                        scan.reset();
                        if (data.matches("^[\\d]+$")) {
                            int id = Integer.parseInt(data);
                            System.out.println("\nEnter the quantity:");
                            data = scan.nextLine();
                            scan.reset();
                            if (data.matches("^[\\d]+$")) {
                                int quantity = Integer.parseInt(data);
                                addBill(id, quantity, myInventory);
                                scan.reset();
                            } else if (data.equalsIgnoreCase("stop")) {
                                if (bill_item[0] == null) {
                                    System.out.println("\nNo bill created.....");
                                } else {
                                    System.out.println(billDisplay(total));
                                    myCustomer.addCustomer(getName(), billDisplay(total));
                                    billReset();
                                }
                            } else {
                                System.out.println("\nwrong input quantity : please try again\nwrite the id and quantity again");
                            }
                        } else if (data.equalsIgnoreCase("stop")) {
                            if (bill_item[0] == null) {
                                System.out.println("\nNo bill created.....");
                            } else {
                                System.out.println(billDisplay(total));
                                myCustomer.addCustomer(getName(), billDisplay(total));
                                billReset();
                            }
                        } else if (data.matches("([\\w&&\\S]+([.]|[*])*([\\w&&\\S])*)+")) {
                            String name = data;
                            scan.reset();
                            System.out.println("\nEnter the quantity:");
                            data = scan.nextLine();
                            scan.reset();
                            if (data.matches("^[\\d]+$")) {
                                int quantity = Integer.parseInt(data);
                                addBill(name, quantity, myInventory);
                                scan.reset();
                            } else if (data.equalsIgnoreCase("stop")) {
                                if (bill_item[0] == null) {
                                    System.out.println("\nNo bill created.....");
                                } else {
                                    System.out.println(billDisplay(total));
                                    myCustomer.addCustomer(getName(), billDisplay(total));
                                    billReset();
                                }
                            } else {
                                System.out.println("\nwrong input quantity : please try again\nwrite the name and quantity again");
                                scan.reset();
                            }
                        } else {
                            System.out.println("\nWrong input");
                            scan.reset();
                        }
                        scan.reset();
                    } while (!data.equalsIgnoreCase("stop"));
                    break;
                case "search":
                    do {
                        System.out.println("\nEnter ID or NAME of the product to search:\nTo stop this work type STOP");
                        data = scan.nextLine();
                        scan.reset();
                        if (data.matches("^[\\d]+$")) {
                            int id = Integer.parseInt(data);
                            myInventory.searchItems(id);
                        } else if (data.equalsIgnoreCase("stop")) {
                            System.out.print("");
                        } else {
                            myInventory.searchItems(data);
                        }
                        scan.reset();
                    } while (!data.equalsIgnoreCase("stop"));
                    break;
                case "additem":
                    do {
                        System.out.println("\nEnter Id of new product :\n To stop this work type STOP");
                        data = scan.nextLine();
                        scan.reset();
                        if (data.matches("^[\\d]+$")) {
                            int id_ = Integer.parseInt(data);
                            System.out.println("\nEnter Name of new product:");
                            data = scan.nextLine();
                            scan.reset();
                            if (data.matches("([\\w&&\\S]+([.]|[*])*([\\w&&\\S])*)+")) {
                                String name_ = data;
                                System.out.println("\nEnter Quantity of new product:");
                                data = scan.nextLine();
                                scan.reset();
                                if (data.matches("^[\\d]+$")) {
                                    int quant_ = Integer.parseInt(data);
                                    System.out.println("\nEnter Price of single piece of new product( price/item ):");
                                    data = scan.nextLine();
                                    scan.reset();
                                    if (data.matches("[\\d]+([.]\\d+)*")) {
                                        double prc_ = Double.parseDouble(data);
                                        myInventory.addItems(id_, name_, quant_, prc_);
                                    } else if (data.equalsIgnoreCase("stop")) {
                                        System.out.print("");
                                    } else {
                                        System.out.println("\nWrong input: type a valid Price");
                                    }
                                } else if (data.equalsIgnoreCase("stop")) {
                                    System.out.print("");
                                } else {
                                    System.out.println("\nWrong input: type a valid Quantity");
                                }
                            } else if (data.equalsIgnoreCase("stop")) {
                                System.out.print("");
                            } else {
                                System.out.println("\nWrong input: type a valid Name");
                            }
                        } else if (data.equalsIgnoreCase("stop")) {
                            System.out.print("");
                        } else {
                            System.out.println("\nWrong input: type a valid Id");
                        }
                        scan.reset();
                    } while (!data.equalsIgnoreCase("stop"));
                    break;
                case "updateitem":
                    do {
                        System.out.println("\nEnter ID or NAME of the product to update:\nTo stop this work type STOP");
                        data = scan.nextLine();
                        scan.reset();
                        if (data.matches("^[\\d]+$")) {
                            int id_ = Integer.parseInt(data);
                            System.out.println("\nEnter quantity to be added to current quantity of this product:");
                            data = scan.nextLine();
                            scan.reset();
                            if (data.matches("^[\\d]+$")) {
                                int quant_ = Integer.parseInt(data);
                                System.out.println("\nEnter new Price of single piece of this product( price/item ):");
                                data = scan.nextLine();
                                scan.reset();
                                if (data.matches("[\\d]+([.]\\d+)*")) {
                                    double prc_ = Double.parseDouble(data);
                                    myInventory.updateItems(id_, quant_, prc_);
                                } else if (data.equalsIgnoreCase("stop")) {
                                    System.out.print("");
                                } else {
                                    System.out.println("\nWrong price : price will not be edited by quantity will be updated");
                                    myInventory.updateItems(id_, quant_);
                                }
                            } else if (data.equalsIgnoreCase("stop")) {
                                System.out.println();
                            } else {
                                System.out.println("\nWrong input");
                            }
                        } else if (data.equalsIgnoreCase("stop")) {
                            System.out.println();
                        } else if (data.matches("([\\w&&\\S]+([.]|[*])*([\\w&&\\S])*)+")) {
                            String name_ = data;
                            System.out.println("\nEnter quantity to be added to current quantity of this product:");
                            data = scan.nextLine();
                            scan.reset();
                            if (data.matches("^[\\d]+$")) {
                                int quant_ = Integer.parseInt(data);
                                System.out.println("\nEnter new Price of single piece of this product( price/item ):");
                                data = scan.nextLine();
                                scan.reset();
                                if (data.matches("[\\d]+([.]\\d+)*")) {
                                    double prc_ = Double.parseDouble(data);
                                    myInventory.updateItems(name_, quant_, prc_);
                                } else if (data.equalsIgnoreCase("stop")) {
                                    System.out.print("");
                                } else {
                                    System.out.println("\nWrong price : price will not be edited by quantity will be updated");
                                    myInventory.updateItems(name_, quant_);
                                }
                            } else if (data.equalsIgnoreCase("stop")) {
                                System.out.println();
                            } else {
                                System.out.println("\nWrong input");
                            }
                        } else {
                            System.out.println("\nWrong input");
                        }
                    } while (!data.equalsIgnoreCase("stop"));
                    break;
                case "allcustomer":
                    myCustomer.displayCustomer();
                    break;
                case "searchcustomer":
                    myCustomer.displayCustomer(getName());
                    break;
                case "quit":
                    System.out.println("\n\n\t\tTHANK YOU \n\t\t BYE.........");
                    break;
                default:
                    System.out.println("\nType a valid task to perform");
                    break;
            }
        } while (!check.equalsIgnoreCase("Quit"));
    }

    private String billDisplay(double total) {
        String message = "--------------------------- B I L L ---------------------------\nProduct_id    Product_name    Product_quantity    Product_price\n\n---------------------------------------------------------------\n";
        for (int i = 0; i < counter1; i++) {
            message = message.concat("   \t" + bill_item[i].productId +
                    "\t\t\t\t" + bill_item[i].productName +
                    "\t\t\t\t" + bill_item[i].productQuantity +
                    "\t\t\t\t" + bill_item[i].productPrice +
                    "\n");
            total = total + bill_item[i].productPrice;
        }
        message = message.concat("---------------------------------------------------------------");
        message = message.concat("\n\t\t\t\t\t\t\t\t\t\tYour Total : " + total + "\n");
        total = discount(total);
        message = message.concat("\n" + discountMessage + "\n");
        message = message.concat("\n\t\t\t\t\t\t\t\t\t\tRonded Total : " + Math.round(total) + "\n");
        message = message.concat("---------------------------------------------------------------\n");
        return message;
    }

    private String getName() {
        System.out.println("\n\t Enter customer name:");
        Scanner sc = new Scanner(System.in);
        sc.reset();
        String name = sc.nextLine();
        sc.reset();
        if (name.matches("^[\\w]+$")) {
            return name;
        } else {
            return getName();
        }
    }

    class customers {
        customers[] customer = new customers[2];
        String customerName;
        String customerBill;
        int counter3;

        customers() {

        }

        customers(String name, String bill) {
            customerName = name;
            customerBill = bill;
        }

        void addCustomer(String name_, String bill_) {
            int j;
            boolean checking = false;
            for (j = 0; j < counter3; j++) {
                if (customer[j].customerName.equals(name_)) {
                    customer[j].customerBill = customer[j].customerBill.concat("\n\n" + bill_);
                    checking = true;
                    break;
                }
            }
            if (!checking) {
                if (counter3 >= 2) {
                    this.customer = Arrays.copyOf(customer, counter3 + 1);
                    customer[counter3++] = new customers(name_, bill_);
                } else {
                    customer[counter3++] = new customers(name_, bill_);
                }
            }
        }

        void displayCustomer() {
            for (int i = 0; i < counter3; i++) {
                System.out.println(customer[i].customerName + "\n" + customer[i].customerBill + "\n\n");
            }
        }

        void displayCustomer(String name) {
            try {
                for (int i = 0; i < counter3; i++) {
                    if (name.equalsIgnoreCase(customer[i].customerName)) {
                        System.out.println(customer[i].customerName + "\n" + customer[i].customerBill + "\n\n");
                        break;
                    } else if (i == counter3 - 1) {
                        throw new ArithmeticException("\nCustomer name not found");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}

class inventory {
    inventory[] inventoryItem = new inventory[2];
    int product_id;
    String product_name;
    int product_quantity;
    double product_price;
    int counter;

    private inventory(int product_id, String product_name, int product_quantity, double product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
    }

    inventory() {
    }

    void addItems(int product_id, String product_name, int product_quantity, double product_price) {
        int i;
        for (i = 0; i < counter; i++) {
            if (inventoryItem[i].product_id == product_id) {
                System.err.println("This id :" + product_id + " already exist");
                break;
            } else if (inventoryItem[i].product_name.equalsIgnoreCase(product_name)) {
                System.err.println("This name :" + product_name + " already exist");
                break;
            }
        }
        if (i == counter) {
            if (counter >= 2) {
                this.inventoryItem = Arrays.copyOf(inventoryItem, counter + 1);
            }
            inventoryItem[counter++] = new inventory(product_id, product_name, product_quantity, product_price);
        }
    }

    void updateItems(int id, int quantity) {
        int i;
        for (i = 0; i < counter; i++) {
            if (inventoryItem[i].product_id == id) {
                inventoryItem[i].product_quantity += quantity;
                break;
            }
        }
        if (i == counter) {
            System.err.println("There is no item with id : " + id);
        }
    }

    void updateItems(String name, int quantity) {
        int i;
        for (i = 0; i < counter; i++) {
            if (inventoryItem[i].product_name.equalsIgnoreCase(name)) {
                inventoryItem[i].product_quantity += quantity;
                break;
            }
        }
        if (i == counter) {
            System.err.println("There is no item with name : " + name);
        }
    }

    void updateItems(int id, int quantity, double price) {
        int i;
        for (i = 0; i < counter; i++) {
            if (inventoryItem[i].product_id == id) {
                inventoryItem[i].product_quantity += quantity;
                inventoryItem[i].product_price = price;
                break;
            }
        }
        if (i == counter) {
            System.err.println("There is no item with id : " + id);
        }
    }

    void updateItems(String name, int quantity, double price) {
        int i;
        for (i = 0; i < counter; i++) {
            if (inventoryItem[i].product_name.equalsIgnoreCase(name)) {
                inventoryItem[i].product_quantity += quantity;
                inventoryItem[i].product_price = price;
                break;
            }
        }
        if (i == counter) {
            System.err.println("There is no item with name : " + name);
        }
    }

    void searchItems(String name) {
        int i;
        for (i = 0; i < counter; i++) {
            if (inventoryItem[i].product_name.equalsIgnoreCase(name)) {
                System.out.println(inventoryItem[i].product_id + "  " + inventoryItem[i].product_name + "  " + inventoryItem[i].product_quantity + "  " + inventoryItem[i].product_price);
                break;
            }
        }
        if (i == counter) {
            System.err.println("There is no item with name : " + name);
        }
    }

    void searchItems(int id) {
        int i;
        for (i = 0; i < counter; i++) {
            if (inventoryItem[i].product_id == id) {
                System.out.println(inventoryItem[i].product_id + "  " + inventoryItem[i].product_name + "  " + inventoryItem[i].product_quantity + "  " + inventoryItem[i].product_price);
                break;
            }
        }
        if (i == counter) {
            System.err.println("There is no item with id : " + id);
        }
    }

    @Override
    public String toString() {
        System.out.println("---------------- INVENTORY OF MY GROCERY STORE ----------------");
        System.out.println("Product_id    Product_name    Product_quantity    Product_price\n" +
                "---------------------------------------------------------------");
        String message = "";
        for (int i = 0; i < counter; i++) {
            message = message.concat("    " + inventoryItem[i].product_id +
                    "           " + inventoryItem[i].product_name + '\'' +
                    "          \t" + inventoryItem[i].product_quantity +
                    "           \t" + inventoryItem[i].product_price +
                    "\n");
        }
        message = message.concat("---------------------------------------------------------------\n");
        return message;
    }
}