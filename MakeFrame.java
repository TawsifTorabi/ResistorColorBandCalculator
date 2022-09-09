//import libraries
import javax.naming.Name;
import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MakeFrame extends JFrame{

    //Color Input
    private final String []ColorBands = {"Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Grey", "White"};
    private final String []MultiplierBand = {"Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Golden", "Silver"};
    private final String []ToleranceBand = {"Brown", "Red","Green", "Blue", "Violet", "Grey", "Golden", "Silver"};
    private final String []PPMBand = {"Brown", "Red", "Orange", "Yellow", "Blue", "Violet"};


    //Band Count Input Section Objects
    private final JLabel BandCountText = new JLabel("Modes: ");
    private final JRadioButton BlankRadio = new JRadioButton();
    private final JRadioButton BandsSelect4 = new JRadioButton("4 Bands");
    private final JRadioButton BandsSelect5 = new JRadioButton("5 Bands");
    private final JRadioButton BandsSelect6 = new JRadioButton("6 Bands");
    private final JPanel BandsUI = new JPanel();
    private JComboBox Color1Box = new JComboBox(ColorBands);
    private JComboBox Color2Box = new JComboBox(ColorBands);
    private JComboBox Color3Box = new JComboBox(ColorBands);
    private JComboBox MultiplierBox = new JComboBox(MultiplierBand);
    private JComboBox ToleranceBox = new JComboBox(ToleranceBand);
    private JComboBox PPMBox = new JComboBox(PPMBand);


    //Button Section Objects
    private final JButton ClearButton = new JButton("Clear");

    //Status Section Objects
    private final JLabel statusOut = new JLabel("I'm Ready, Select a Mode!");
    private final JLabel resultOut = new JLabel("");
    private final JLabel RangeOut = new JLabel("");
    private final JLabel ResultLabel = new JLabel("Resistance Value: ");
    private final JLabel MinMaxLabel = new JLabel("Resistance Range: ");




    //Method to get Selected Band Count from JRadioButtons
    public String getBandCounts() {
        String BandCount = null;
        if(BandsSelect4.isSelected()){
            BandCount = "4";
        }else if(BandsSelect5.isSelected()){
            BandCount = "5";
        }else if(BandsSelect6.isSelected()){
            BandCount = "6";
        } else {
            BandCount = null;
        }
        return BandCount;
    }

    public void Calculate(ActionEvent e){
        if(!ResultLabel.isVisible()){ ResultLabel.setVisible(true);}
        if(!resultOut.isVisible()){ resultOut.setVisible(true);}
        int []ColorBandType_1 = {0,1,2,3,4,5,6,7,8,9};
        double []Multipliers = {1,10,100,1000,10000,100000,1000000,10000000,0.1,0.01};
        String []Tolerances = {"1%","2%","0.5%","0.25%","0.1%","0.05%","5%","10%"};
        String []ppm = {"100ppm","50ppm","15ppm","25ppm","10ppm","5ppm"};
        int BandCount = Integer.parseInt(getBandCounts());
        String []result1 = new String[3];

        int Band1 = ColorBandType_1[Color1Box.getSelectedIndex()];
        int Band2 = ColorBandType_1[Color2Box.getSelectedIndex()];
        int Band3 = ColorBandType_1[Color3Box.getSelectedIndex()];
        double MulBand = Multipliers[MultiplierBox.getSelectedIndex()];
        String TolBand = Tolerances[ToleranceBox.getSelectedIndex()];
        String PPMBand = ppm[PPMBox.getSelectedIndex()];

        if(BandCount == 4){
            result1 = new MainFunctions().BandCalculator(
                        BandCount,
                        Band1, Band2,
                    -1,
                        MulBand, TolBand,
                    ""
                );
        }
        if(BandCount == 5){
            result1 = new MainFunctions().BandCalculator(
                    BandCount,
                    Band1, Band2, Band3,
                    MulBand, TolBand,
                    ""
            );
        }
        if(BandCount == 6){
            result1 = new MainFunctions().BandCalculator(
                    BandCount, Band1, Band2, Band3, MulBand, TolBand, PPMBand
            );
        }

        System.out.println(result1);
        resultOut.setText(result1[0]);

        //Check If the Tolerance is Passed
        if(result1[1] != null){
            String MinMaxText = "Max - " + result1[1] + "      " + "Min - " + result1[2];
            RangeOut.setText(MinMaxText);
            MinMaxLabel.setVisible(true);
            RangeOut.setVisible(true);
        }
    }


    //Method to Select Mode
    public void SelectModeInit(ActionEvent e) {
        int modeSelect;
        if(getBandCounts() == null){
            statusOut.setText("Please Select Mode!");
            modeSelect = 0;
        }else{
            String bandCountsNum = getBandCounts();
            statusOut.setText(bandCountsNum + " Bands Resistor Selected");
            modeSelect = Integer.parseInt(bandCountsNum);
            System.out.println(bandCountsNum + " Bands Resistor Selected");

            Color1Box.addActionListener(this::Calculate);
            Color2Box.addActionListener(this::Calculate);
            Color3Box.addActionListener(this::Calculate);
            MultiplierBox.addActionListener(this::Calculate);
            ToleranceBox.addActionListener(this::Calculate);
            PPMBox.addActionListener(this::Calculate);

            if (modeSelect == 4){
                BandsUI.setVisible(false);
                BandsUI.removeAll();
                //Add the radio buttons to the Jpanel container with flow layout
                BandsUI.add(new JToolBar.Separator());
                BandsUI.add(Color1Box);
                BandsUI.add(Color2Box);
                BandsUI.add(MultiplierBox);
                BandsUI.add(ToleranceBox);
                BandsUI.setVisible(true);
                System.out.println("NUM 1" + " Four Bands Triggered");
            } else if(modeSelect == 5){
                BandsUI.setVisible(false);
                BandsUI.removeAll();
                //Add the radio buttons to the Jpanel container with flow layout
                BandsUI.add(new JToolBar.Separator());
                BandsUI.add(Color1Box);
                BandsUI.add(Color2Box);
                BandsUI.add(Color3Box);
                BandsUI.add(MultiplierBox);
                BandsUI.add(ToleranceBox);
                BandsUI.setVisible(true);
                System.out.println("NUM 2" + " Five Bands Triggered");
            } else if(modeSelect == 6){
                BandsUI.setVisible(false);
                BandsUI.removeAll();
                //Add the radio buttons to the Jpanel container with flow layout
                BandsUI.add(new JToolBar.Separator());
                BandsUI.add(Color1Box);
                BandsUI.add(Color2Box);
                BandsUI.add(Color3Box);
                BandsUI.add(MultiplierBox);
                BandsUI.add(ToleranceBox);
                BandsUI.add(PPMBox);
                BandsUI.setVisible(true);
                System.out.println("NUM 3" + " Six Bands Triggered");
            }
        }
    }

    //Method to clear the form
    public void actionClear(ActionEvent e){
        statusOut.setText("I'm Ready, Select a Mode!");
        BlankRadio.setSelected(true);
        BandsSelect4.setSelected(false);
        BandsSelect5.setSelected(false);
        BandsSelect6.setSelected(false);
        ResultLabel.setVisible(false);
        resultOut.setVisible(false);
        MinMaxLabel.setVisible(false);
        RangeOut.setVisible(false);
        BandsUI.setVisible(false);
        BandsUI.removeAll();
    }

    //Constructor
    public MakeFrame(){
        super("Resistor Color Code Calculator");
        setSize(530,230);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Get Main Containers for contents
        Container mainContainer = this.getContentPane();
        //set layout for main container
        mainContainer.setLayout(new GridLayout(6,1));

        //Create Row Header Text
        JPanel Header = new JPanel();
        //Set Flow Layout for Header with CENTER alignment
        Header.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
        JLabel HeaderText = new JLabel("Resistor Color Code Calculator", SwingConstants.CENTER);
        Header.add(new JToolBar.Separator());
        HeaderText.setFont(new Font("Arial", Font.PLAIN, 20));
        Header.add(HeaderText);
        mainContainer.add(Header);


        //Create Row Panel 1
        JPanel panel1 = new JPanel();
        //Set Flow Layout for Row Panel 1
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
        //Band Count Mode Selector Area
        panel1.add(new JToolBar.Separator());
        panel1.add(BandCountText);
        //Create button group to restrict user from selecting more than one radio option
        var btnGroup = new ButtonGroup();
        btnGroup.add(BlankRadio);
        btnGroup.add(BandsSelect4);
        btnGroup.add(BandsSelect5);
        btnGroup.add(BandsSelect6);

        //Add the radio buttons to the Jpanel container with flow layout
        BandsSelect4.addActionListener(this::SelectModeInit);
        BandsSelect5.addActionListener(this::SelectModeInit);
        BandsSelect6.addActionListener(this::SelectModeInit);
        BlankRadio.setVisible(false);
        panel1.add(BlankRadio);
        panel1.add(BandsSelect4);
        panel1.add(BandsSelect5);
        panel1.add(BandsSelect6);
        ClearButton.addActionListener(this::actionClear);
        //Buttons Area
        panel1.add(new JToolBar.Separator());
        panel1.add(ClearButton);
        panel1.add(new JToolBar.Separator());
        //Add the Jpanel container to the main container with grid layout
        mainContainer.add(panel1);



        //Create Band Calculator Panel
        //Set Flow Layout for BandsUI
        BandsUI.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 2));
        BandsUI.setVisible(false);
        mainContainer.add(BandsUI);

        //Create Row Panel 2
        //Containing Status Text
        JPanel panel2 = new JPanel();
        //Set Flow Layout for Panel 2
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 2));
        //Text Area
        panel2.add(new JToolBar.Separator());
        panel2.add(statusOut);
        statusOut.setFont(new Font("Arial", Font.BOLD, 12));
        mainContainer.add(panel2);


        //Create Row Panel 3
        //Containing Status Text
        JPanel panel3 = new JPanel();
        //Set Flow Layout for Panel 2
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 6));

        //Text Area
        panel3.add(new JToolBar.Separator());
        ResultLabel.setVisible(false);
        ResultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel3.add(ResultLabel);
        resultOut.setFont(new Font("Arial", Font.BOLD, 14));
        panel3.add(resultOut);
        mainContainer.add(panel3);


        //Create Row Panel 3
        //Containing Status Text
        JPanel panel4 = new JPanel();
        //Set Flow Layout for Panel 2
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 6));

        //Text Area
        panel4.add(new JToolBar.Separator());
        MinMaxLabel.setVisible(false);
        RangeOut.setFont(new Font("Arial", Font.BOLD, 12));
        MinMaxLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel4.add(MinMaxLabel);
        panel4.add(RangeOut);
        mainContainer.add(panel4);


        setVisible(true);
        setResizable(false);
    }
}
