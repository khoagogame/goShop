package PageUI;

public class APIKeyPageUI {
    public static final String API_KEYS_PAGE_TITLE = "//h5";
    public static final String CREATE_NEW_API_KEY_FORM_TITLE = "//h5[text()='Create New API Key']";
    public static final String ADD_NEW_BUTTON = "//a[contains(@href,'creat')]";
    public static final String KEY_NAME_WARNING = "//label[@id='key_name-error']";
    public static final String LIST_HOST_WARNING = "//label[@id='allow_host-error']";
    public static final String STATUS_WARNING = "//label[@id='status-error']";
    public static final String SUBMIT_BUTTON = "//button[@type='submit']";
    public static final String CANCEL_BUTTON = "//a[@class='btn btn-secondary']";
    public static final String KEY_NAME_TEXT_BOX = "//input[@id='key_name']";
    public static final String INPUT_LIST_HOST_TEXTAREA = "//textarea[@id='allow_host']";
    public static final String STATUS_RADIO_BUTTON = "//span[text()='%s']/parent::label";
    public static final String API_KEY_GENERATED_SUCCESSFULLY_MESSAGE = "//div[contains(@class,'success')]";
    public static final String DELETE_API_KEY_BUTTONS = "//button[contains(@title,'Delete')]";
    public static final String DELETE_API_KEY_BUTTONS_BY_KEY_NAME = "//span[text()='Partner']/ancestor::tr//button";
    public static final String API_KEY_PAGE_KEY_NAME = "//label[contains(text(),'Key Name: ')]/following-sibling::span";
    public static final String API_KEY_PAGE_API_STATUS = "//td[4]/span";
    public static final String API_KEY_PAGE_HOST_NAME = "//td[3]//li";
    public static final String EDIT_BUTTON_BY_KEY_NAME = "//span[text()='%s']/ancestor::tr//a";
    public static final String MANAGE_API_KEYS_HYPERLINK = "//a[contains(text(),'Manage API Keys')]";
    public static final String DELETE_API_KEY_CONFIRMATION_POPUP = "//div[@class='swal-modal']";
    public static final String DELETE_CONFIRMATION_CANCEL_BUTTON = "//button[text()='Cancel']";
    public static final String DELETE_CONFIRMATION_OK_BUTTON = "//button[text()='OK']";
    public static final String DELETE_API_KEY_SUCCESS_MESSAGE = "//div[@class='swal-modal']/div[2]";
    public static final String DELETE_API_KEY_SUCCESS_POPUP_OK_BUTTON = "//div[@class='swal-modal']//button[text()='OK']";
    public static final String SEARCH_TEXTBOX = "//input[@type='search']";


}
