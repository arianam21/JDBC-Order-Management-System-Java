package Presentation;
/**
 * Controller class for MainView.
 * Handles button clicks and opens corresponding management views.
 */
public class MainController {
    private MainView view;
    /**
     * Constructs the MainController and attaches action listeners
     * to buttons in the MainView.
     *
     * @param view the MainView instance to control
     */
    public MainController(MainView view) {
        this.view = view;

        view.clientButton.addActionListener(e -> {
            new ClientController(new ClientView());
        });

        view.productButton.addActionListener(e -> {
            new ProductController(new ProductView());
        });

        view.orderButton.addActionListener(e -> {
            new OrderController(new OrderView());
        });
    }
}
