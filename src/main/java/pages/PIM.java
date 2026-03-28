package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class PIM {
	WebDriver driver;

	public PIM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[normalize-space()='Add Employee']")
	private WebElement getAddEmplyoee;

	@FindAll({ @FindBy(xpath = "//input [@name='firstName' ]"), @FindBy(xpath = "//input[@placeholder='First Name']"),
			@FindBy(xpath = "//input[@placeholder='First Name' and @name ='firstName']") })
	private WebElement firstName;

	@FindAll({ @FindBy(xpath = "//input[contains(@class, 'oxd-input oxd-input--active orangehrm-lastname')]"),
			@FindBy(xpath = "//input[contains(@name, 'lastName')]"),
			@FindBy(xpath = "//input[contains(@placeholder, 'Last Name')]"), @FindBy(name = "lastName")

	})
	private WebElement lastName;

	@FindAll({ @FindBy(xpath = "//label[normalize-space()='Employee Id']/parent::div/following-sibling::div//input"),
			@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']"),
			@FindBy(xpath = "//label[text()='Employee Id']/following::input[1]"),

	})
	private WebElement employeeId;

	@FindAll({ @FindBy(xpath = "//button[normalize-space()='Save']"), @FindBy(xpath = "//button[text()=' Save ']") })
	private WebElement save;

	@FindAll({ @FindBy(xpath = "//div[contains(@class,'oxd-toast-container oxd-toast-container--bottom') ]"),
			@FindBy(xpath = "//p[normalize-space()='Successfully Saved' ]") })
	private WebElement toastMessage;

	@FindBy(xpath = "//label[normalize-space()='Employee Id']/parent::div/following-sibling::div//input")
	private WebElement generatedEmployeeidDetails;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	private WebElement search;
	
	

	@FindAll({
			@FindBy(xpath = "//div[@class='oxd-table-card-cell-checkbox']//span[contains(@class, 'oxd-checkbox-input')]"),
			@FindBy(xpath = "//div[@class='oxd-table-card-cell-checkbox']"),
			@FindBy(xpath = "//div[@class='oxd-table-card-cell-checkbox']//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input']")

	})
	private WebElement checkBox;

	@FindBy(xpath = "//button[contains(@class,'oxd-icon-button oxd-table-cell-action-space')]//i[contains(@class,'oxd-icon bi-trash')]")
	private WebElement trash;

	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
	private WebElement yesDelete;
	
	@FindBy(xpath = "//span[normalize-space()='No Records Found']")
	private WebElement noRecord;
	
	@FindBy(xpath="//span[text()='(1) Record Found']")
	private WebElement recordFound;
	
	@FindBy(xpath = "//label[text()='Employee Name']/parent::div/following-sibling::div//input")
	 private WebElement empName;
	

	public WebElement getGeneratedEmployeeidDetails() {
		return generatedEmployeeidDetails;
	}

	public WebElement getEmpName() {
		return empName;
	}

	public WebElement getRecordFound() {
		return recordFound;
	}

	public WebElement getNoRecord() {
		return noRecord;
	}

	public WebElement getYesDelete() {
		return yesDelete;
	}

	public WebElement getTrash() {
		return trash;
	}

	public WebElement getCheckBox() {
		return checkBox;
	}

	public WebElement getSearch() {
		return search;
	}

	public WebElement getEmployeeidDetails() {
		return generatedEmployeeidDetails;
	}

	public WebElement getToastMessage() {
		return toastMessage;
	}


	public WebElement getSave() {
		return save;
	}

	public WebElement getAddEmplyoee() {
		return getAddEmplyoee;
	}

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getEmployeeId() {
		return employeeId;
	}

	public void addEmployee(String eFName, String eLName, String empId) {
		getAddEmplyoee.click();
		firstName.sendKeys(eFName);
		lastName.sendKeys(eLName);
		save.click();

	}

	public String GeneratedEmployeeId() {

		// getAttribute("value") isliye kyunki ID ek input box mein

		return getEmployeeidDetails().getAttribute("value");
	}
 public  String GeneratedEmployeeName() {
	 return getFirstName().getAttribute("value");
 }
}
