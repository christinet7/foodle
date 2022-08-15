import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.Arrays;


public class Word extends JFrame implements ActionListener {
  private JLabel label1, label2, label3, label4, label5, label6, label7 ;
  private static JTextField textField1 ;
  private static JPanel panel3 ;
  private static JButton enterButton, boostButton ;
  private static int numTurns = 0 ;
  private static String correctWord = "" ;
  private static boolean usedBoost = false ;
  public static final Color SEA_GREEN = new Color( 46, 139, 87 ) ;
  public static final Color MINT_CREAM = new Color( 245, 255, 250 ) ;

  // build jframe in constructor 
  public Word() {
    super( "foodle" ) ;
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ) ;
    setSize( 600, 600 ) ;
    setLayout(new BoxLayout( getContentPane(), BoxLayout.Y_AXIS )) ;
    Font font = new Font("Monospaced", Font.BOLD, 25);

    //******create panel 1 - foodle label
    JPanel panel1 = new JPanel();
    panel1.setBackground( MINT_CREAM ) ;
    JLabel title = new JLabel("f o o d l e") ;
  
    title.setFont( font );
    title.setForeground( SEA_GREEN );
    title.setIcon(new ImageIcon("cherry.png"));
    panel1.add( title );

    // create info panel
    JPanel infoPanel = new JPanel() ;
    infoPanel.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;
    infoPanel.setBackground( MINT_CREAM ) ;
    JLabel info = new JLabel( "how to play: use your 6 turns to guess a 5-letter, food-related word.", SwingConstants.LEFT  ) ;
    JLabel info2 = new JLabel( "orange letters indicate the letter is in the wrong spot." ) ; 
    JLabel info3 = new JLabel( "green letters indicate the letter is in the right spot." );
    JLabel info4 = new JLabel( "black letters indicate the letter is not in the word." );
    JLabel info5 = new JLabel( "use the boost button 1 time to get an extra turn." ) ;
    infoPanel.add( info ) ;
    infoPanel.add( info2 ) ;
    infoPanel.add( info3 ) ;
    infoPanel.add( info4 ) ;
    infoPanel.add( info5 ) ;
    

    //******create panel 2 - text field 1 and label 1 to display user's input
    JPanel panel2 = new JPanel() ;
    panel2.setLayout( new FlowLayout() ) ;
    panel2.setBackground( MINT_CREAM ) ;

    // create textfield
    textField1 = new JTextField( "", 5 ) ;
    textField1.setHorizontalAlignment( JTextField.RIGHT ) ;

    // create enter button 
    enterButton = new JButton( "enter" ) ;
    enterButton.setForeground( Color.BLACK ) ;
    enterButton.addActionListener( this );
    enterButton.setBackground( SEA_GREEN ) ;

    // create hint button
    boostButton = new JButton( "boost" ) ;
    boostButton.setForeground( Color.BLACK ) ;
    boostButton.addActionListener( this );
    boostButton.setBackground( SEA_GREEN ) ;

    // add components to panel2
    panel2.add( textField1 ) ;
    panel2.add( enterButton ) ;
    panel2.add( boostButton ) ;
    
   
    //**********create panel 3 - labels to dusplay the user's input / turns 
    panel3 = new JPanel() ;
    panel3.setLayout( new BoxLayout( panel3, BoxLayout.Y_AXIS ));

    label1 = new JLabel( "-----" ) ;
    label1.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;
    
    label2 = new JLabel( "-----" ) ;
    label2.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;

    label3 = new JLabel( "-----" ) ;
    label3.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;

    label4 = new JLabel( "-----" ) ;
    label4.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;

    label5 = new JLabel( "-----" ) ;
    label5.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;

    label6 = new JLabel( "-----" ) ;
    label6.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;

    label7 = new JLabel( "-----" ) ;
    label7.setBorder( new EmptyBorder(10, 10, 10, 10) ) ;
    label7.setVisible( false ) ;

    // add labels to panel3
    panel3.add( label1 ) ;
    panel3.add( label2 ) ;
    panel3.add( label3 ) ;
    panel3.add( label4 ) ;
    panel3.add( label5 ) ;
    panel3.add( label6 ) ;
    panel3.add( label7 ) ;

    // add panels to jframe
    add( panel1 ) ;
    add( infoPanel ) ;
    add( panel2 ) ;
    add( panel3 ) ;
    setVisible( true ) ;
  }

  // ****** generates a word
  public static String WordGenerator() {
    String[] wordList = new String[ 10 ] ;
    wordList[0] = "apple" ;
    wordList[1] = "bacon" ;
    wordList[2] = "candy" ;
    wordList[3] = "broil" ;
    wordList[4] = "cream" ;
    wordList[5] = "mango" ;
    wordList[6] = "sushi" ;
    wordList[7] = "wheat" ;
    wordList[8] = "guava" ;
    wordList[9] = "thyme" ;

    // (Math.random() * (max - min + 1)) + min 
    int randInt = (int) ( Math.random() * 10) ;
    correctWord += wordList[ randInt ] ;
    //System.out.println("correctword: " + correctWord ) ;
    return correctWord ;
  }


  // first checks user's input ( by getting textField's text ) against the correct word and sets the label's text w/ corresponding colored letters to indicate whether letters inputted are (a) in the correct word, and (b) in the correct position 
  public static void Checker ( JLabel label, JTextField text, String word ) {
    // create array of letters for user's input
    String input = text.getText() ;
    ArrayList <Character> userArr = new ArrayList <Character>() ;
    //char[] userArr = new char[ 5 ] ;
    for( int i = 0; i < 5; i++ ) { userArr.add( input.charAt(i) ) ; }

    // create array of letters for correct word
    ArrayList <Character> correctArr = new ArrayList <Character>() ;
    for( int i = 0; i < 5; i++ ) { correctArr.add( word.charAt(i) ) ; }

    // create array accum
    int[] displayText = new int[5] ;
    
    // checks if letter is in word, if letter is in correct position, and then if word is correct
    for( int i = 0; i < 5; i++ ) {
        if( correctArr.contains( userArr.get(i) ) ) {
          
          if( userArr.get(i).equals( correctArr.get(i) ) ) { displayText[i] = 2 ; }
          
          else { displayText[i] = 1; }
        }
          
      else if( ! correctArr.contains( userArr.get(i) ) ) { displayText[i] = 0; }
      }  
    
    // debugging print statements
    //System.out.println( Arrays.toString(displayText) ) ;
    //System.out.println( userArr ) ;
    //System.out.println( correctArr ) ;
    colorChanger( displayText, label, input, panel3 );  

    // check if user got the word correct and display option pane if game over
    if( userArr.equals( correctArr ) || (usedBoost == false && numTurns == 6 ) || (usedBoost == true && numTurns == 7 ) ) {
      enterButton.setEnabled( false ) ;
        
      if( ( numTurns == 6 && ! userArr.equals( correctArr )) || ( usedBoost == true && numTurns == 7 && ! userArr.equals( correctArr )) ) {
        JOptionPane.showMessageDialog( null, "game over! the correct word was " + correctWord, "game over!",  JOptionPane.INFORMATION_MESSAGE) ;
      }
        
      if( userArr.equals( correctArr ) ) {
        JOptionPane.showMessageDialog( null, "superb!", "you win!", JOptionPane.INFORMATION_MESSAGE) ;
      }
    }
  }

  public void actionPerformed( ActionEvent evt )  {
    //System.out.println( "Button pressed!" ) ;
    JButton pressedButton = (JButton)evt.getSource() ;

    // if enter button is pressed:
    if( pressedButton == enterButton ) {
      numTurns++ ;
      String input = textField1.getText() ;
    
      // determine which label's text to change depending on numTurns
      JLabel label = new JLabel() ;
      switch( numTurns ) {
        case 1 : 
          label = label1 ;
          break ;
        case 2 :
          label = label2 ;
          break ;
        case 3 : 
          label = label3 ;
          break ;
        case 4 : 
          label = label4 ;
          break ;
        case 5 : 
          label = label5 ;
          break ;
        case 6 : 
          label = label6 ;
          break ;
        case 7 :
          label = label7 ;
          break ;
      }
      Checker(label, textField1, correctWord ) ;
    }

    if( pressedButton == boostButton ) { 
      int randInt = (int) ( Math.random() * 5) ;
      String popupText = "" ;
      switch( randInt ) {
        case 0 :
          popupText = "do different colored froot loops taste different?" ;
          break ;
        case 1 :
          popupText = "are the holes in crackers pointless?" ;
          break ;
        case 2:
          popupText = "do oranges always have more vitamin c than peppers?" ;
          break ;
        case 3:
          popupText = "are fruit snacks coated in a different type of wax that is found on cars?" ;
          break ;
        case 4:
          popupText = "is white chocolate actually chocolate?" ;
          break ;
      }
         
      
      String popupTitle = "answer yes or no to get a boost!" ;
      int answer = JOptionPane.showConfirmDialog( this, popupText, popupTitle, JOptionPane.YES_NO_OPTION ) ;

      if( answer  == JOptionPane.NO_OPTION ) {
        usedBoost = true ;
        label7.setVisible( true ) ;
      }
    }
  }

  public static void colorChanger( int[] numSequence, JLabel label, String input, JPanel panel )  {
    // create new array of the colors each letter should be
    String[] arr = new String[5] ;
    for( int i = 0 ; i < arr.length; i++ ) {
      if( numSequence[i] == 2 ) { arr[i] = "green" ; } 
      
      if( numSequence[i] == 1  ) { arr[i] = "orange" ; }
      
      if( numSequence[i] == 0 ) { arr[i] = "black" ; }
    }
    
    String displayString = "" ;
    
    for( int i = 0 ; i < arr.length ; i++ ) {
      displayString = displayString + "<html><font color=" + arr[i] + ">" + input.charAt(i) + "</font>" ;
    }
    
    label.setText( displayString ) ;
  }

  public static void main(String[] args) {
    // generate word 
    WordGenerator() ;
    Word word = new Word() ;
  }
}