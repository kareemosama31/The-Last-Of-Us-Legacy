package views;


import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import engine.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.characters.Hero;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;

import model.world.CharacterCell;
import model.world.CollectibleCell;

import model.world.TrapCell;

public class start extends Application implements EventHandler<KeyEvent>{
Stage window;
Scene scene,scene1;
Pane root1;
Hero h;
 Label[][] b= new Label[15][15];
 ArrayList <ToggleButton> a= new ArrayList<ToggleButton>(8);


public static ArrayList<Button> c= new ArrayList<Button>(8);
public static JTextArea details;
private static final int MAP_SIZE = 15;
private static final int CELL_SIZE = 40;
public static Game game ;
public static Hero chosenHero;
public static Cell[][]map= new Cell[Game.BOARDHEIGHT][Game.BOARDWIDTH] ;
// This is your pre-initialized map



public static void main(String[] args) 
		throws Exception {
launch(args);
}
	public void start(Stage stage) throws Exception{
		//Game.startGame(Game.getAvailableHeroes().get(0));
		
		Game game = new Game();
		
		String csvFile = "Heros.csv";
		Game.loadHeroes(csvFile);
		Cell[][] map = game.getMap();

		
	//BASE	
    window = stage;
	Image icon = new Image("game.png");
	stage.getIcons().add(icon);
	stage.setTitle("The Last Of Us Legacy");
	
    
 //Scene 1-------------------------------------------------------
    Pane root = new Pane();
    Text text = new Text("PRESS ENTER TO CONTINUE!!!!");
    text.setX(250);
    text.setY(700);
    text.setFont(Font.font("Verdana",30));
    
    var image = new Image("R.jpg", true);
    var bgImage = new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
    );
    root.getChildren().addAll(text);
    root.setBackground(new Background(bgImage));    
    Scene scene = new Scene(root, 1000, 800);
 //---------------------------------------------------------------
 //Scene 2--------------------------------------------------------
   
    Pane root1 = new Pane();
    Text text1 = new Text("Choose a character");
     text1.setX(250);
     text1.setY(700);
     text1.setFont(Font.font("Verdana",30));
     root1.getChildren().addAll(text1);
     Rectangle rectangle = new Rectangle();
     rectangle.setX(150);
     rectangle.setY(100);
     rectangle.setWidth(700);
     rectangle.setHeight(500);
     rectangle.setFill(Color.GREEN);
//     rectangle.setStroke(Color.DARKGREEN);
     rectangle.setStrokeWidth(5);
     rectangle.setArcHeight(30);
     rectangle.setArcWidth(30);
     Line line = new Line(200,110,200,590);
     line.setTranslateX(120);
     line.setStrokeWidth(20);
     Line line1 = new Line(200,110,200,590);
     line1.setTranslateX(300);
     line1.setStrokeWidth(20);
     Line line2 = new Line(200,110,200,590);
     line2.setTranslateX(480);
     line2.setStrokeWidth(20);
     Line line3 = new Line(850,590,170,590);
     line3.setTranslateX(-10);
     line3.setTranslateY(-240);
     line3.setStrokeWidth(20);
    ToggleButton Joel = new ToggleButton();
     Joel.setStyle("-fx-background-image: url('Joel.png')");
     Joel.setMaxSize(200, 200);
     Joel.setTranslateX(147);
     Joel.setTranslateY(100);
     Joel.setMinWidth(170);
     Joel.setMinHeight(240);
     ToggleButton Ellie = new ToggleButton();
     Ellie.setStyle("-fx-background-image: url('Ellie.jpg')");
     Ellie.setMaxSize(200, 200);
     Ellie.setTranslateX(325);
     Ellie.setTranslateY(100);
     Ellie.setMinWidth(170);
     Ellie.setMinHeight(240);
     ToggleButton Tess = new ToggleButton();
     Tess.setStyle("-fx-background-image: url('tess.jpg')");
     Tess.setMaxSize(200, 200);
     Tess.setTranslateX(507);
     Tess.setTranslateY(100);
     Tess.setMinWidth(170);
     Tess.setMinHeight(240);
     ToggleButton Riley = new ToggleButton();
     Riley.setStyle("-fx-background-image: url('Riley2.png')");
     Riley.setMaxSize(200, 200);
     Riley.setTranslateX(685);
     Riley.setTranslateY(100);
     Riley.setMinWidth(170);
     Riley.setMinHeight(240);
     ToggleButton Tommy = new ToggleButton();
     Tommy.setStyle("-fx-background-image: url('Tommy1.png')");
     Tommy.setMaxSize(200, 200);
     Tommy.setTranslateX(147);
     Tommy.setTranslateY(360);
     Tommy.setMinWidth(170);
     Tommy.setMinHeight(240);
     ToggleButton Bill = new ToggleButton();
     Bill.setStyle("-fx-background-image: url('Bill3.png')");
     Bill.setMaxSize(200, 200);
     Bill.setTranslateX(330);
     Bill.setTranslateY(360);
     Bill.setMinWidth(170);
     Bill.setMinHeight(240);
     ToggleButton David = new ToggleButton();
     David.setStyle("-fx-background-image: url('David3.png')");
     David.setMaxSize(200, 200);
     David.setTranslateX(510);
     David.setTranslateY(360);
     David.setMinWidth(170);
     David.setMinHeight(240);
    
    
     ToggleButton Henry = new ToggleButton();
     Henry.setStyle("-fx-background-image: url('Henry.png')");

     Henry.setMinHeight(40);
     Henry.setMaxSize(200, 200);
     Henry.setTranslateX(685);
     Henry.setTranslateY(360);
     Henry.setMinWidth(170);
     Henry.setMinHeight(240);
  
     
     ToggleButton done = new ToggleButton();
     done.setStyle("PICK THIS HERO");
     
  
     
//    
//     Joel.setOnMouseClicked(new EventHandler<MouseEvent>() {
//		public void handle(MouseEvent k) {
//			Button Done = new Button("Done");
//			root1.getChildren().add(Done);
//			root1.setTranslateX(200);
//			root1.setTranslateY(200);
//			root1.setMinWidth(170);
//			root1.setMinHeight(240);
//		}
//    			 
//     }
//
//		
//			
//		);
     a.add(Joel);
     a.add(Ellie);
     a.add(Tess);
     a.add(Riley);
     a.add(Tommy);
     a.add(Bill);
     a.add(David);
     a.add(Henry);
     done.setAlignment(Pos.BOTTOM_LEFT);
     done.setOnAction(event -> {
    	 map m= new map();
    	 Stage nextPage= new Stage();
    	 try {
			m.start(nextPage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 window.close();});
    	 
     

     
     
     
     
     Text s = new Text("f");
     s.setVisible(false);
    
     s.setFont(new Font(20));
     s.prefWidth(200);
     s.prefHeight(200);
     VBox container = new VBox(s);
 
     container.setLayoutX(750);
     container.setLayoutY(600);
     root1.getChildren().addAll(container,rectangle,line,line1,line2,line3,Joel,Ellie,Tess,Riley,Tommy,Bill,David,Henry,done);
   
     Joel.setOnMouseEntered(new EventHandler<MouseEvent>() {
     
		@Override
		public void handle(MouseEvent e) {
			s.setVisible(true);
			 h=Game.getAvailableHeroes().get(0);
			String f=   h.toString();
			    s.setText(f);
			  
		}
    	 
     });
     Joel.setOnMouseExited(new EventHandler<MouseEvent>() {

 		@Override
 		public void handle(MouseEvent e) {
 			s.setVisible(false);
 			
 		}
     	 
      });
     
     
     Ellie.setOnMouseEntered(new EventHandler<MouseEvent>() {

 		@Override
 		public void handle(MouseEvent e) {
 			s.setVisible(true);
 			h=game.getAvailableHeroes().get(1);
 			//System.out.println("h"+h);
 			String f=h.toString();
 			s.setText(f);
 		}
     	 
      });
      Ellie.setOnMouseExited(new EventHandler<MouseEvent>() {

  		@Override
  		public void handle(MouseEvent e) {
  			s.setVisible(false);
  			
  		}
      	 
       });
      Tess.setOnMouseEntered(new EventHandler<MouseEvent>() {

  		@Override
  		public void handle(MouseEvent e) {
  			s.setVisible(true);
  			h=game.getAvailableHeroes().get(2);
  			String f=h.toString();
  			s.setText(f);
  		}
      	 
       });
       Tess.setOnMouseExited(new EventHandler<MouseEvent>() {

   		@Override
   		public void handle(MouseEvent e) {
   			s.setVisible(false);
   			
   		}
       	 
        });
       Riley.setOnMouseEntered(new EventHandler<MouseEvent>() {

   		@Override
   		public void handle(MouseEvent e) {
   			s.setVisible(true);
   			h=game.getAvailableHeroes().get(3);
   			String f=h.toString();
   			s.setText(f);
   		}
       	 
        });
        Riley.setOnMouseExited(new EventHandler<MouseEvent>() {

    		@Override
    		public void handle(MouseEvent e) {
    			s.setVisible(false);
    			
    		}
        	 
         });
        Tommy.setOnMouseEntered(new EventHandler<MouseEvent>() {

    		@Override
    		public void handle(MouseEvent e) {
    			s.setVisible(true);
    			h=game.getAvailableHeroes().get(4);
    			String f=h.toString();
    			s.setText(f);
    		}
        	 
         });
         Tommy.setOnMouseExited(new EventHandler<MouseEvent>() {

     		@Override
     		public void handle(MouseEvent e) {
     			s.setVisible(false);
     			
     		}
         	 
          });
         Bill.setOnMouseEntered(new EventHandler<MouseEvent>() {

     		@Override
     		public void handle(MouseEvent e) {
     			s.setVisible(true);
     			h=game.getAvailableHeroes().get(5);
     			String f=h.toString();
     			s.setText(f);
     		}
         	 
          });
          Bill.setOnMouseExited(new EventHandler<MouseEvent>() {

      		@Override
      		public void handle(MouseEvent e) {
      			s.setVisible(false);
      			
      			
      		}
          	 
           });
          David.setOnMouseEntered(new EventHandler<MouseEvent>() {

      		@Override
      		public void handle(MouseEvent e) {
      			s.setVisible(true);
      			h=game.getAvailableHeroes().get(6);
      			String f=h.toString();
      			s.setText(f);
      		}
          	 
           });
           David.setOnMouseExited(new EventHandler<MouseEvent>() {

       		@Override
       		public void handle(MouseEvent e) {
       			s.setVisible(false);
       			
       		}
           	 
            });
           Henry.setOnMouseEntered(new EventHandler<MouseEvent>() {

       		@Override
       		public void handle(MouseEvent e) {
       			s.setVisible(true);
       			h=Game.getAvailableHeroes().get(7);
       			
       			String f=h.toString();
       			
       			s.setText(f);
       		}
           	 
            });
            Henry.setOnMouseExited(new EventHandler<MouseEvent>() {

        		@Override
        		public void handle(MouseEvent e) {
        			s.setVisible(false);
        			
        		}
            	 
             });
            
//            Joel.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    for (ToggleButton b : a) {
//                        if (event.getSource() == b) {
//                            // Get the selected hero's name from the ToggleButton's text
//                            String selectedHeroName = b.getText();
//
//                            // Split the selected hero's name into first and last names
//                            String[] nameParts = selectedHeroName.split(" ");
//                            String firstName = nameParts[0];
//                           
//
//                            // Find the corresponding hero object from the available heroes in the Game class
//                            for (Hero c1 : Game.getAvailableHeroes()) {
//                                if (c1.getName().equals(firstName)) {
//                                    chosenHero = c1;
//                                    
//                                    
//                                    break;
//                                }
//                                
//                            }
//                            break;
//                        }
//                    }
//                }
//     });
           
       //    Game.setHero(chosenHero);
       
     
     
     
     
    
//----------------------------------------------------------------
//SCENE 3---------------------------------------------------------
//            if (chosenHero == null) {
//    	        // Set a default hero if no hero has been chosen
//    	        chosenHero = game.getAvailableHeroes().get(0);
//        	}
//        	done.setOnMousePressed(new EventHandler<MouseEvent>() {
//	            public void handle(MouseEvent ke) {
//	            	chosenHero = h;
//	            	Scene3 scene3 = new Scene3(map,chosenHero);
//	            	Pane root2 = new Pane();
//	                root2.getChildren().add(scene3);
//	             
//	            	Scene z= new Scene(root2,1000,800);
//                	window.setScene(z);
//                   ke.consume();
//	            }            
//	        }
//	    );
//        	//System.out.print("ArrayList"+ game.getAvailableHeroes());
//     Scene3 scene3 = new Scene3(map,chosenHero);        	

     // Create a new Scene object and add the Scene3 object to it
    

     // Create a new Scene3 object with the map
     
     
     // Create a new Scene with the Pane and set it as the scene for the stage
     
     
    

             // Add the cell to the GridPane
       
//
//     // Iterate through the map and create a new cell for each entry
//     for (int row = 0; row < MAP_SIZE; row++) {
//         for (int col = 0; col < MAP_SIZE; col++) {
//             Cell cell = map[row][col];
//             if (col > 0) {
//            	    Line line5 = new Line(0, 0, 0, CELL_SIZE);
//            	    GridPane.setConstraints(line, col, row);
//            	    root2.getChildren().add(line);
//            	}
//             // Create the appropriate type of cell based on the subclass of Cell
//             Rectangle rectangle1 = null;
//             if (cell instanceof CollectibleCell) {
//                 rectangle1 = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GREEN);
//             } else
//if (cell instanceof TrapCell) {
//                 rectangle1 = new Rectangle(CELL_SIZE, CELL_SIZE, Color.RED);
//             } else if (cell instanceof CharacterCell) {
//                 rectangle1 = new Rectangle(CELL_SIZE, CELL_SIZE, Color.BLUE);
//             } else {
//                 rectangle1 = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
//             }
//
//             // Add the cell to the GridPane
//             root2.getChildren().addAll(rectangle1);
//         }
//     }

     // Create a Scene and add the GridPane to it
     

     // Set the size of the Stage and show it
     
   
 
	

      
//----------------------------------------------------------------
//Scene Changer---------------------------------------------------	        
         
	        
	        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            public void handle(KeyEvent ke) {
	                if(ke.getCode()==KeyCode.ENTER) {
	                	
	                	Scene x= new Scene(root1,1000,800);
	                	window.setScene(x);
	                   ke.consume();
	                }
	                
	               
	     
	                
	            }
	        });
	        
	      
	        Joel.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
	            	h=Game.getAvailableHeroes().get(0);
	            	
	            	chosenHero = h;
	            	
	            }            
	        }
	    );
	       
	        Ellie.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
	               
	                	h=Game.getAvailableHeroes().get(1);
		            	chosenHero = h;
	                	
	                
	                
	               
	                
	                
	                
	                
	            }
	        });
	        Tess.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
	            	h=Game.getAvailableHeroes().get(2);
	            	chosenHero = h;
	                	
	               
	                
	                
	                
	                
	            }
	        });
	        Riley.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
	            	h=Game.getAvailableHeroes().get(3);
	            	chosenHero = h;
	                	
	                
	               
	                
	                
	                
	                
	            }
	        });
	        Tommy.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
	            	h=Game.getAvailableHeroes().get(4);
	            	chosenHero = h;
	                
	                
	                
	                
	                
	            }
	        });
	        Bill.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
					h = Game.getAvailableHeroes().get(5);
					chosenHero = h;
	                
	                
	                
	                
	            }
	        });
	        David.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
	            	h=Game.getAvailableHeroes().get(6);
	            	chosenHero = h;
	                
	                
	                
	            }
	        });
	        Henry.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent ke) {
	            	h=Game.getAvailableHeroes().get(7);
	            	chosenHero = h;
	            	
	                	
	                
	                
	                
	                
	            }
	        });
	       
	      
//----------------------------------------------------------------
	        
		    stage.setScene(scene);
	        window.show();

	}
	

	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	class Done implements EventHandler<ActionEvent>{
	    @Override
	    public void handle(ActionEvent event){
	     
	        
	        

	        //Create new imageView
	      

	        
	    }
	}
	
//	 public void displayMap(Scene3 scene3) {
//	        // add the Scene3 object to a pane or scene to display it
//	        Pane root2 = new Pane();
//	        root2.getChildren().add(scene3);
//	        Scene z = new Scene(root2, 1000, 800);
//	        Stage stage = new Stage();
//	        stage.setScene(z);
//	        stage.show();
//	    }
//	
// 
//	

	  public void mouseEntered(MouseEvent e) {
  		
  		for(int i=0;i<start.c.size();i++) {
  			if(e.getSource()==start.c.get(i)) {
  				Hero h=game.getAvailableHeroes().get(i);
  				start.details.setText(h.toString());
  			}
  				
  		}
  		
  		
  		
  	}
	  public void updateBoard() {
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					b[i][j].setText(null);
				}
			}
			for(int j=14;j>=0;j--) {
				for(int i=0;i<game.getMap()[j].length;i++) {
					Object o=game.getMap()[j][i];
					if(o instanceof CollectibleCell) {
						if(o instanceof Vaccine ) {
							
						
						b[j][i].setText("V "  );
						//b[j][i].setBackground(Color.AZURE);
						}
						else if (o instanceof Supply ) {
							b[j][i].setText("S"  );
						}
					}
					
					if (o instanceof CharacterCell&& o!=null) {
						
							
						
						b[j][i].setText(((CharacterCell) o).getCharacter().getName()+" " +'\n' +"HP:"+'\n'  + ((CharacterCell)o).getCharacter().getCurrentHp());
						
					
					
				}
			}
		}
	  }
	
}

