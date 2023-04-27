import javax.swing.*;

public class View
{
    public AbstractButton printCSV = new JButton("Create");//creating instance of JButton;
    public AbstractButton SaltCSVButton = new JButton("Salt");//creating instance of JButton;
    public AbstractButton SmoothCSVButton = new JButton("Smooth");//creating instance of JButton;
    public AbstractButton GraphCSVButton = new JButton("Graph");//creating instance of JButton;
    public AbstractButton clearCSVButton = new JButton("clear");//creating instance of JButton;

    private JFrame f = new JFrame();//creating instance of JFrame
    //private JButton calculateButton = new JButton("Calculate");//creating instance of JButton
    //private JButton printCSV = new JButton("Print to csv");//creating instance of JButton

    private JLabel inputLabel = new JLabel("Enter a Range: ");
    private JLabel formulaLabel = new JLabel("x^2 + x");
    private JTextField inputTextX = new JTextField("");
    private JTextField inputTextZ = new JTextField("");

    private JTextField salterField = new JTextField("");
    private JTextField gritField = new JTextField("");
    private JTextField windowField = new JTextField("");


    private JLabel salterText = new JLabel("(1)Salt (2)Grit = ");
    private JLabel windowText = new JLabel("Window Size =  ");


    private JLabel outputPrecursor = new JLabel("Formula = ");
    private JLabel outputActual = new JLabel("");


    public String returnInputX()
    {
        return this.inputTextX.getText();
    }
    public String returnInputZ()
    {
        return this.inputTextZ.getText();
    }
    public String returnSaltValue()
    {
        return this.salterField.getText();
    }
    public String returnWindowValue()
    {
        return this.windowField.getText();
    }
    public String returnGritValue()
    {
        return this.gritField.getText();
    }
    public void display()
    {
        configureItems();
        addToDisplay();
    }
    public void reInit()
    {
        salterField.setText("");
        gritField.setText("");
        inputTextX.setText("");
        inputTextZ.setText("");
        windowField.setText("");
    }


    private void configureItems()
    {
        f.setTitle("MVC CSV");
        //calculateButton.setBounds(230,100,100, 40); //x axis, y axis, width, height
        printCSV.setBounds(130,100+80,200, 40);
        SaltCSVButton.setBounds(130,140+80,100, 40); //x axis, y axis, width, height
        SmoothCSVButton.setBounds(230,140+80,100, 40); //x axis, y axis, width, height
        GraphCSVButton.setBounds(130,200+80, 200, 40);
        clearCSVButton.setBounds(130,240+80, 200, 40);


        inputTextX.setBounds(130,60,100, 40);
        inputTextZ.setBounds(230,60,100, 40);
        windowField.setBounds(130, 140, 100, 40);
        salterField.setBounds(130, 100, 100, 40);
        gritField.setBounds(230, 100, 100, 40);


        windowText.setBounds(30, 140, 100, 40);
        formulaLabel.setBounds(130,20,100, 40);
        inputLabel.setBounds(30,60,100, 40);
        salterText.setBounds(30, 100, 100, 40);
        outputPrecursor.setBounds(60,20,100, 40);
        outputActual.setBounds(130,200,100, 40);
    }
    private void addToDisplay()
    {
        f.add(SmoothCSVButton);
        f.add(SaltCSVButton);
        f.add(GraphCSVButton);
        f.add(clearCSVButton);

        //f.add(calculateButton);//adding button in JFram
        f.add(inputLabel);     //adding button in JFrame
        f.add(formulaLabel);   //adding button in JFrame
        f.add(inputTextX);
        f.add(inputTextZ);
        f.add(salterText);

        f.add(salterField);
        f.add(windowField);
        f.add(gritField);


        f.add(windowText);
        f.add(outputPrecursor);
        f.add(outputActual);
        f.add(printCSV);
        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);     //using no layout managers
        f.setVisible(true);    //making the frame visible
    }


}
