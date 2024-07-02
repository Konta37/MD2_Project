package entity;

import feature.CategoryService;
import utils.IOData;

import java.util.Scanner;

public class Category implements IOData {
    private int categoryId;
    private String categoryName;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        this.categoryId = inputCateId();
        this.categoryName = inputCategoryName(sc);
        this.description = inputCategoryDescription(sc);
        this.status = inputCategoryStatus(sc);
    }

    @Override
    public void displayData() {
        String format = "| %-12s | %-20s | %-30s | %-10s |\n";
        String separator = "+--------------+----------------------+--------------------------------+------------+\n";

        System.out.print(separator);
        System.out.format(format, "Category ID", "Category Name", "Description", "Status");
        System.out.print(separator);
        System.out.format(format, categoryId, categoryName, description, status);
        System.out.print(separator);
    }

    private int inputCateId(){
        int max=-1;
        if (CategoryService.categoryList.isEmpty()){
            return 0;
        }
        for(int i = 1; i< CategoryService.categoryList.size(); i++){
            if(CategoryService.categoryList.get(i).getCategoryId()>max){
                max=CategoryService.categoryList.get(i).getCategoryId();
            }
        }
        if(max!=-1){
            return max+1;
        }else {
            return 0;
        }
    }

    public String inputCategoryName(Scanner sc){
        System.out.println("Enter category name: ");
        do {
            String categoryName = sc.nextLine();
            if (!categoryName.isEmpty()){
                return categoryName;
            }else {
                System.err.println("Invalid category name (Cannot be empty)");
            }
        }while (true);
    }

    public String inputCategoryDescription(Scanner sc){
        System.out.println("Enter category description: ");
        return sc.nextLine();
    }

    public boolean inputCategoryStatus(Scanner sc){
        System.out.println("Enter category status: ");
        do {
            String status = sc.nextLine();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            }else {
                System.err.println("Invalid status value (true/false). Try an other one!");
            }
        }while (true);
    }
}
