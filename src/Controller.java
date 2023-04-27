import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller
{
    View view = new View();

    /**
     * Method that contains all the action listeners for the GUI
     */
    public void manipulator()
    {
        SaltPlotSmootherAPI SpsA = new SaltPlotSmootherAPI();
        view.display();

        view.printCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("created" +Integer.parseInt(view.returnInputX()));
                //model.createCSV(view.returnInputX(), view.returnInputZ());
                SpsA.standardCsv("Standard Quadratic API GUI.csv", Integer.parseInt(view.returnInputX()), 1+Integer.parseInt(view.returnInputZ()));
            }
        });
        view.SaltCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Salted: "+ Integer.parseInt(view.returnInputX()));

                SpsA.saltCSV("Standard Quadratic API GUI.csv", "Quadratic GUI", Integer.parseInt(view.returnInputX()), 1+Integer.parseInt(view.returnInputZ()), Integer.parseInt(view.returnSaltValue()));

            }
        });
        view.SmoothCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Smoothed" + Integer.parseInt(view.returnInputX()));
                SpsA.smoothCSV("Salted Quadratic GUI API.csv", "Quadratic GUI", Integer.parseInt(view.returnWindowValue()), Integer.parseInt(view.returnGritValue()),  Integer.parseInt(view.returnInputX()), 1+Integer.parseInt(view.returnInputZ()));

            }
        });
        view.GraphCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Graphed");
                SpsA.frontEndTester( Integer.parseInt(view.returnInputX()), 1+Integer.parseInt(view.returnInputZ()),"Standard Quadratic API GUI.csv", "Salted Quadratic GUI API.csv", "Smoothed Quadratic GUI API.csv");
            }
        });

        view.clearCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Cleared");
                view.reInit();
            }
        });


    }

}
