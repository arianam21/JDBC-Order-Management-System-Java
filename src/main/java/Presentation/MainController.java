package Presentation;

public class MainController {
    private MainView view;

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
