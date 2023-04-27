import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains the action listeners for the View class
 */
public class Controller
{
    View Viewer = new View();

    /**
     * Method that contains all the action listeners for the GUI
     */
    public void manipulator()
    {
        SaltPlotSmootherAPI SpsA = new SaltPlotSmootherAPI();
        Viewer.display();

        Viewer.PrintCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("created");
                SpsA.standardCsv("Standard Quadratic API GUI.csv", Integer.parseInt(Viewer.returnInputX()), 1+Integer.parseInt(Viewer.returnInputZ()));
            }
        });
        Viewer.SaltCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Salted");
                SpsA.saltCSV("Standard Quadratic API GUI.csv", "Quadratic GUI", Integer.parseInt(Viewer.returnInputX()), 1+Integer.parseInt(Viewer.returnInputZ()), Integer.parseInt(Viewer.returnSaltValue()));

            }
        });
        Viewer.SmoothCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Smoothed");
                SpsA.smoothCSV("Salted Quadratic GUI API.csv", "Quadratic GUI", Integer.parseInt(Viewer.returnWindowValue()), Integer.parseInt(Viewer.returnGritValue()),  Integer.parseInt(Viewer.returnInputX()), 1+Integer.parseInt(Viewer.returnInputZ()));

            }
        });
        Viewer.GraphCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Graphed");
                SpsA.frontEndTester( Integer.parseInt(Viewer.returnInputX()), 1+Integer.parseInt(Viewer.returnInputZ()),"Standard Quadratic API GUI.csv", "Salted Quadratic GUI API.csv", "Smoothed Quadratic GUI API.csv");
            }
        });

        Viewer.ClearCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Cleared");
                Viewer.reInit();
            }
        });
    }
}
