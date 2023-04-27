import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * View portion of the mvc that runs the GUI, this defines, initializes and displays the GUI
 */
public class View
{
    public AbstractButton PrintCSV = new JButton("Create");//creating instance of JButton;
    public AbstractButton SaltCSVButton = new JButton("Salt");//creating instance of JButton;
    public AbstractButton SmoothCSVButton = new JButton("Smooth");//creating instance of JButton;
    public AbstractButton GraphCSVButton = new JButton("Graph");//creating instance of JButton;
    public AbstractButton ClearCSVButton = new JButton("clear");//creating instance of JButton;

    private JFrame f = new JFrame();//creating instance of JFrame

    private JLabel InputLabel = new JLabel("Enter a Range: ");
    private JLabel FormulaLabel = new JLabel("x^2 + x");
    private JTextField InputTextX = new JTextField("");
    private JTextField InputTextZ = new JTextField("");

    private JTextField SalterField = new JTextField("");
    private JTextField GritField = new JTextField("");
    private JTextField WindowField = new JTextField("");


    private JLabel SalterText = new JLabel("(1)Salt (2)Grit = ");
    private JLabel WindowText = new JLabel("Window Size =  ");


    private JLabel OutputPrecursor = new JLabel("Formula = ");
    private JLabel OutputActual = new JLabel("");


    public String returnInputX()
    {
        return this.InputTextX.getText();
    }
    public String returnInputZ()
    {
        return this.InputTextZ.getText();
    }
    public String returnSaltValue()
    {
        return this.SalterField.getText();
    }
    public String returnWindowValue()
    {
        return this.WindowField.getText();
    }
    public String returnGritValue()
    {
        return this.GritField.getText();
    }



    /**
     * Used to display the graph
     */
    public void display()
    {
        configureItems();
        addToDisplay();
    }

    /**
     * Method to quickly clear the data in all the input fields
     */
    public void reInit()
    {
        SalterField.setText("");
        GritField.setText("");
        InputTextX.setText("");
        InputTextZ.setText("");
        WindowField.setText("");
    }

    /**
     * Formatting of the GUI
     */
    private void configureItems()
    {
        f.setTitle("MVC CSV");
        PrintCSV.setBounds(130,100+80,200, 40);
        SaltCSVButton.setBounds(130,140+80,100, 40); //x axis, y axis, width, height
        SmoothCSVButton.setBounds(230,140+80,100, 40); //x axis, y axis, width, height
        GraphCSVButton.setBounds(130,200+80, 200, 40);
        ClearCSVButton.setBounds(130,240+80, 200, 40);

        InputTextX.setBounds(130,60,100, 40);
        InputTextZ.setBounds(230,60,100, 40);
        WindowField.setBounds(130, 140, 100, 40);
        SalterField.setBounds(130, 100, 100, 40);
        GritField.setBounds(230, 100, 100, 40);

        WindowText.setBounds(30, 140, 100, 40);
        FormulaLabel.setBounds(130,20,100, 40);
        InputLabel.setBounds(30,60,100, 40);
        SalterText.setBounds(30, 100, 100, 40);
        OutputPrecursor.setBounds(60,20,100, 40);
        OutputActual.setBounds(130,200,100, 40);
    }

    /**
     * Adds all the items in configureItems() to the GUI and sets them to be visible
     */
    private void addToDisplay()
    {
        f.add(SmoothCSVButton);
        f.add(SaltCSVButton);
        f.add(GraphCSVButton);
        f.add(ClearCSVButton);

        f.add(InputLabel);     //adding button in JFrame
        f.add(FormulaLabel);   //adding button in JFrame
        f.add(InputTextX);
        f.add(InputTextZ);
        f.add(SalterText);

        f.add(SalterField);
        f.add(WindowField);
        f.add(GritField);


        f.add(WindowText);
        f.add(OutputPrecursor);
        f.add(OutputActual);
        f.add(PrintCSV);
        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);     //using no layout managers
        f.setVisible(true);    //making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
