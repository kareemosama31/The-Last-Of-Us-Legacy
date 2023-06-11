package views;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class map extends Application {
	public static Cell[][] map = Game.getMap();
	TextField text;
	private static final int MAP_SIZE = 15;
	private static final int CELL_SIZE = 40;
	public static Hero ChosenCharacter = null;
	TextField  textBox;
	public static Zombie ChosenTarget = null;
	public static Hero MedicTarget = null;
	GridPane gp;
	BorderPane root ;

	boolean m5taro = false;
	StackPane sp;
	Rectangle [] [] r= new Rectangle [15][15];
	Rectangle rectarngle;
	Alert alert;
	Text t = new Text();
	Text win = new Text();
	@Override
	public void start(Stage stage) throws Exception {
		 alert = new Alert(AlertType.INFORMATION);
		Game game = new Game();

		BorderPane root = new BorderPane();
		GridPane gp = new GridPane();
		
//		root.setCenter(gp);
//		root.setPadding(new Insets(10));
		Game.startGame(start.chosenHero);
		System.out.println(start.chosenHero.getLocation());
		System.out.println(start.chosenHero.toString());
	
		

		for (int i = 0; i < MAP_SIZE; i++) {
			RowConstraints rowConstraints = new RowConstraints(CELL_SIZE);
			gp.getRowConstraints().add(rowConstraints);
		}
		for (int i = 0; i < MAP_SIZE; i++) {
			ColumnConstraints colConstraints = new ColumnConstraints(CELL_SIZE);
			gp.getColumnConstraints().add(colConstraints);
		}

		// Iterate through the map and create a new cell for each entry
		for (int row = 0; row < MAP_SIZE; row++) {
			for (int col = 0; col < MAP_SIZE; col++) {
				Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
				

				// Create the appropriate type of cell based on the subclass of Cell

//				if (map[row][col] instanceof CollectibleCell) {
//					if (((CollectibleCell) map[row][col]).getCollectible() instanceof Vaccine
//							&& map[row][col].isVisible()) {
//
//						rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GOLD);
//
//						 System.out.println("Vaccine visible");
//					}
//
//					else if (((CollectibleCell) map[row][col]).getCollectible() instanceof Vaccine
//							&& !map[row][col].isVisible()) {
//
//						rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.PURPLE);
//						System.out.println("Vaccine not visible");
//					}
//
//					if (((CollectibleCell) map[row][col]).getCollectible() instanceof Supply
//							&& map[row][col].isVisible()) {
//
//						rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.PINK);
//						 System.out.println("Supply Visible");
//					}
//
//					else if (((CollectibleCell) map[row][col]).getCollectible() instanceof Supply
//							&& !map[row][col].isVisible()) {
//
//						rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.BROWN);
//						 System.out.println("Supply not visible");
//					}
//				} else if (map[row][col] instanceof TrapCell&&map[row][col].isVisible()) {
//					rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GOLD);
//					 System.out.println(2);
//				} else if (map[row][col] instanceof CharacterCell) {
//					if (((CharacterCell) map[row][col]).getCharacter() != null) {
//						if (((CharacterCell) map[row][col]).getCharacter() instanceof Hero) {
//
//							rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.BLUE);
//							 System.out.println(3);
//						}
//						if (((CharacterCell) map[row][col]).getCharacter() == null&&((CharacterCell) map[row][col]).isVisible()) {
//							rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GOLD);
//						}
//						if (((CharacterCell) map[row][col]).getCharacter() instanceof Zombie) {
//							rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GREEN);
//							
//							 System.out.println(0);
//						}
//
//					}
//					else if (((CharacterCell) map[row][col]).getCharacter() == null&&((CharacterCell) map[row][col]).isVisible()) {
//						rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GOLD);
//					}
//				}
//				
//
//				else {
//					rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.BLACK);
//				}
				
				rectangle.setOnMouseClicked(this::handleGridClick);
     		    rectangle.setVisible(map[row][col].isVisible());
     			
				
				
				r[row][col]=rectangle;

				// Add the cell to the GridPane

				gp.add(rectangle, col, row);
				root.setCenter(gp);
			}
			//gp.setAlignment(Pos.CENTER);

		}
		updateMap();
//Label l = new Label("HIIIII");
//l.setAlignment(Pos.TOP_RIGHT);
//		HBox h = new HBox();
//	    h.setAlignment(Pos.TOP_RIGHT);
//	   h.getChildren().add(l);
//	   h.setTranslateY(200);
//
//		root.setTop(h);
////LEFT INFO 
//		if (ChosenCharacter== null) {
//			l.setText("HIIII");
//		}
//		else {
//		l.setText(ChosenCharacter.CurrenttoString());
//		
//		}
	
		
			
//BUTTONSSS
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BOTTOM_RIGHT);
		grid.setPadding(new Insets(10));
		grid.setTranslateX(-120);
		Button up = new Button("up");
		up.setPrefWidth(80);
		up.setPrefHeight(50);
		up.setBackground(new Background(new BackgroundFill(
				Color.web("2E86C1"), null, null)));
		up.setStyle("-fx-border-width: 3px; -fx-border-color:BLACK;");
		up.setId("up");
		up.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
				try {
					if(ChosenCharacter==null)
						return;
					int myHp = ChosenCharacter.getCurrentHp();
					ChosenCharacter.move(Direction.UP);
					if(myHp>ChosenCharacter.getCurrentHp()) {
						
						alert.setTitle("TRAPCELL DANGER");
						alert.setHeaderText(null);
						alert.setContentText("Ouchhhhh");

						alert.showAndWait();

					}
					if(ChosenCharacter.getSupplyInventory()!=null||ChosenCharacter.getVaccineInventory()!=null)
						t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
					for(int i = 0; i <15;i++) {
						for( int j = 0; j<15;j++){
			r[i][j].setVisible(map[i][j].isVisible());
//			r[i][j]= new Rectangle(CELL_SIZE,CELL_SIZE,Color.GOLD);
		}
					}
					updateMap();
					 System.out.println(ChosenCharacter.getLocation());
					System.out.println("UP");
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("WRONG MOVE");
					

					alert.showAndWait();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("NOT ENOUGH ACTIONS");
					alert.showAndWait();
				}
			}
		});
		
		

		Button down = new Button("down");
		down.setPrefWidth(80);
		down.setPrefHeight(50);
		down.setBackground(new Background(new BackgroundFill(Color
				.web("2E86C1"), null, null)));
		down.setStyle("-fx-border-width: 3px; -fx-border-color: BLACK;");
		down.setId("down");
		down.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
				try {
					if(ChosenCharacter==null)
						return;
					int myHp = ChosenCharacter.getCurrentHp();
					ChosenCharacter.move(Direction.DOWN);
if(myHp>ChosenCharacter.getCurrentHp()) {
						
						alert.setTitle("TRAPCELL DANGER");
						alert.setHeaderText(null);
						alert.setContentText("Ouchhhhh");

						alert.showAndWait();

					}
					if(ChosenCharacter.getSupplyInventory()!=null||ChosenCharacter.getVaccineInventory()!=null)
						t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
					for(int i = 0; i <15;i++) {
						for( int j = 0; j<15;j++){
			r[i][j].setVisible(map[i][j].isVisible());
//			r[i][j]= new Rectangle(CELL_SIZE,CELL_SIZE,Color.GOLD);
		}
					}
					updateMap();
					 System.out.println(ChosenCharacter.getLocation());
					System.out.println("UP");
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("WRONG MOVE");

					alert.showAndWait();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("NOT ENOUGH ACTIONS");
					alert.showAndWait();
				}
			}
		});
	
		
		Button left = new Button("left");
		left.setPrefWidth(80);
		left.setPrefHeight(50);
		left.setBackground(new Background(new BackgroundFill(Color
				.web("2E86C1"), null, null)));
		left.setStyle("-fx-border-width: 3px; -fx-border-color: BLACK;");
		left.setId("left");
		left.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
				try {
					
					if(ChosenCharacter==null)
						return;
					int myHp = ChosenCharacter.getCurrentHp();

					ChosenCharacter.move(Direction.LEFT);
if(myHp>ChosenCharacter.getCurrentHp()) {
						
						alert.setTitle("TRAPCELL DANGER");
						alert.setHeaderText(null);
						alert.setContentText("Ouchhhhh");

						alert.showAndWait();

					}
					if(ChosenCharacter.getSupplyInventory()!=null||ChosenCharacter.getVaccineInventory()!=null)
						t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
					
					for(int i = 0; i <15;i++) {
						for( int j = 0; j<15;j++){
			r[i][j].setVisible(map[i][j].isVisible());
//			r[i][j]= new Rectangle(CELL_SIZE,CELL_SIZE,Color.GOLD);
		}
					}
					updateMap();
					 System.out.println(ChosenCharacter.getLocation());
					System.out.println("UP");
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("WRONG MOVE");

					alert.showAndWait();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("NOT ENOUGH ACTIONS");
					alert.showAndWait();
				}
			}
		});
	
		
		Button right = new Button("right");
		right.setPrefWidth(80);
		right.setPrefHeight(50);
		right.setBackground(new Background(new BackgroundFill(Color
				.web("2E86C1"), null, null)));
		right.setStyle("-fx-border-width: 3px; -fx-border-color: BLACK;");
		right.setId("right");
		right.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
				try {
					if(ChosenCharacter==null)
						return;
					int myHp = ChosenCharacter.getCurrentHp();
					ChosenCharacter.move(Direction.RIGHT);
if(myHp>ChosenCharacter.getCurrentHp()) {
						
						alert.setTitle("TRAPCELL DANGER");
						alert.setHeaderText(null);
						alert.setContentText("Ouchhhhh");

						alert.showAndWait();

					}
					if(ChosenCharacter.getSupplyInventory()!=null||ChosenCharacter.getVaccineInventory()!=null)
						t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
					 
				    
					for(int i = 0; i <15;i++) {
						for( int j = 0; j<15;j++){
			r[i][j].setVisible(map[i][j].isVisible());
			
//			r[i][j]= new Rectangle(CELL_SIZE,CELL_SIZE,Color.GOLD);
		}
					}
					updateMap();
					 System.out.println(ChosenCharacter.getLocation());
					System.out.println("UP");
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("WRONG MOVE");

					alert.showAndWait();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("NOT ENOUGH ACTIONS");
					alert.showAndWait();
				}
			}
		});
	
		grid.setHgap(10);
		grid.setVgap(10);
		grid.add(up, 1, 0, 1, 1);
		grid.add(down, 1, 1, 1, 1);
		grid.add(left, 0, 1, 1, 1);
		grid.add(right, 2, 1, 1, 1);
		root.setRight(grid);
		
//RIGHT INFO-------------------------------------------------------------------------------
		
		
	  
	
//ACTION BUTTONS-------------------------------------------------------------------------------------------------
		VBox buttonBox = new VBox(10);
		buttonBox.setAlignment(Pos.BOTTOM_LEFT);
		buttonBox.setPadding(new Insets(10));

		Button attack = new Button("ATTACK!");
		attack.setPrefWidth(90);
		attack.setPrefHeight(50);
		attack.setBackground(new Background(new BackgroundFill(Color
				.web("2E86C1"), null, null)));
		attack.setStyle("-fx-border-width: 3px; -fx-border-color: #601502;  -fx-text-fill: white;");
		attack.setId("attack");
		attack.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
				
					try {
						ChosenCharacter.attack();
						updateMap();
						if(ChosenCharacter.getSupplyInventory()!=null||ChosenCharacter.getVaccineInventory()!=null)
							t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
						System.out.println(ChosenCharacter.getTarget().getCurrentHp());
					} catch (InvalidTargetException e) {
						alert.setTitle("ERROR");
						alert.setHeaderText(null);
						alert.setContentText("INVALID TARGET");
						alert.showAndWait();
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert.setTitle("ERROR");
						alert.setHeaderText(null);
						alert.setContentText("NOT ENOUGH ACTIONS!");
						alert.showAndWait();
					}
					
					updateMap();
				
			}
			});
		

		// attack.setAlignment(Pos.CENTER);
		Button end = new Button("END TURN?");
		end.setPrefWidth(90);
		end.setPrefHeight(50);
		end.setBackground(new Background(new BackgroundFill(Color
				.web("2E86C1"), null, null)));
		end.setStyle("-fx-border-width: 3px; -fx-border-color: #601502;  -fx-text-fill: white;");
		end.setAlignment(Pos.TOP_RIGHT);
		end.setId("end");
		end.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
				
					try {
						game.endTurn();
						updateMap();
						if(ChosenCharacter.getSupplyInventory()!=null||ChosenCharacter.getVaccineInventory()!=null)
							t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
						MedicTarget= null;
//						System.out.println(ChosenCharacter.getCurrentHp());
						
					} catch (InvalidTargetException e) {
						alert.setTitle("ERROR");
						alert.setHeaderText(null);
						alert.setContentText("INVALID TARGET");
						alert.showAndWait();
					} catch (NotEnoughActionsException e) {
						alert.setTitle("ERROR");
						
						
						alert.setHeaderText(null);
						alert.setContentText("NOT ENOUGH ACTIONS");
						alert.showAndWait();
					}
					
				 
			}});

		Button cure = new Button("CURE!");
		cure.setPrefWidth(90);
		cure.setPrefHeight(50);
		cure.setBackground(new Background(new BackgroundFill(Color
				.web("2E86C1"), null, null)));
		cure.setStyle("-fx-border-width: 3px; -fx-border-color: #601502;  -fx-text-fill: white;");
		cure.setId("cure");
		cure.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
			
				try {
					ChosenCharacter.cure();
					updateMap();
					if(ChosenCharacter.getSupplyInventory()!=null||ChosenCharacter.getVaccineInventory()!=null)
						t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
				} catch (NoAvailableResourcesException e) {
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("NO AVAILABLE RESOURCES!");
					alert.showAndWait();
					e.printStackTrace();
				} catch (InvalidTargetException e) {
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("INVALID TARGET");
					alert.showAndWait();
				} catch (NotEnoughActionsException e) {
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("NOT ENOUGH ACTIONS");
					alert.showAndWait();
				}
				
			
			
			}
			});
		
				

		Button specialAction = new Button("USE SPECIAL ACTION!");
		specialAction.setPrefWidth(200);
		specialAction.setPrefHeight(50);
		specialAction.setBackground(new Background(new BackgroundFill(Color
				.web("2E86C1"), null, null)));

		specialAction
				.setStyle("-fx-border-width: 3px; -fx-border-color: #601502;  -fx-text-fill: white;");
		specialAction.setId("specialAction");
		specialAction.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ke) {
				
					try {
						ChosenCharacter.useSpecial();
						updateMap();
						t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());;
						 
					} catch (InvalidTargetException e) {
						alert.setTitle("ERROR");
						alert.setHeaderText(null);
						alert.setContentText("INVALID TARGET");
						alert.showAndWait();
						e.printStackTrace();
					} catch (NoAvailableResourcesException e) {
						alert.setTitle("ERROR");
						alert.setHeaderText(null);
						alert.setContentText("NO AVAILABLE RESOURCES");
						alert.showAndWait();
					} catch (NotEnoughActionsException e) {
						alert.setTitle("ERROR");
						alert.setHeaderText(null);
						alert.setContentText("NOT ENOUGH ACTIONS");
						alert.showAndWait();
					}
				
				
			}
		});
			
		// specialAction.setAlignment(Pos.CENTER_LEFT);

		buttonBox.getChildren().addAll(attack, cure, specialAction,end);
		
		root.setLeft(buttonBox);
		
		
	   
		
		
		
	   // text.setPrefSize(100, 100);
//	text.setStyle("-fx-text-fill: black; -fx-background-color: Black;-fx-font-size: 20 px");
	  
		
		
		
		// textBox.setPadding(new Insets(10));
		
		//textBox.setMinWidth(300);
	  //  textBox.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
		
		root.setTop(t);
		//root.setRight(text);
		updateMap();
		
		Scene scene3 = new Scene(root, 1000, 800);
		stage.setScene(scene3);
		stage.show();
		
	}
		private void handleGridClick(MouseEvent event) {
			Rectangle clickedRectangle = (Rectangle) event.getSource();    
			  
			   int rowIndex = GridPane.getRowIndex(clickedRectangle);      
			   int colIndex = GridPane.getColumnIndex(clickedRectangle);  
			       
			   if (ChosenCharacter == null && m5taro == false) {  
			               
			      if (map[rowIndex][colIndex] instanceof CharacterCell &&     
			         ((CharacterCell) map[rowIndex][colIndex]).getCharacter() instanceof Hero) {
			          
			         Hero hero = (Hero) ((CharacterCell) map[rowIndex][colIndex]).getCharacter();         
			         
			         ChosenCharacter = hero;                  
			        	t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
			         clickedRectangle.setStroke(Color.BLACK);            
			         m5taro = true;    
			       }       
			    }     
			   else if(ChosenCharacter!=null){
			        
			      if (map[rowIndex][colIndex] instanceof CharacterCell && 
			           ((CharacterCell) map[rowIndex][colIndex]).getCharacter() instanceof Hero) {
			                        
			           Hero newHero = (Hero) ((CharacterCell) map[rowIndex][colIndex]).getCharacter();               
			           ChosenCharacter = newHero;      
			           t.setText(ChosenCharacter.CurrenttoString()+" " + ChosenCharacter.InventoryToString());
			           
			         }
			         
			       if(ChosenCharacter instanceof Medic) {  
			        
			           if(map[rowIndex][colIndex] instanceof CharacterCell && 
			              ((CharacterCell) map[rowIndex][colIndex]).getCharacter() instanceof Hero){
			                 
			                 MedicTarget = (Hero) ((CharacterCell) map[rowIndex][colIndex]).getCharacter(); 
			                 ChosenCharacter.setTarget(MedicTarget);
			           }
			           else if(map[rowIndex][colIndex] instanceof CharacterCell && 
			              ((CharacterCell) map[rowIndex][colIndex]).getCharacter() instanceof Zombie) {
			        	   ChosenTarget= (Zombie) ((CharacterCell) map[rowIndex][colIndex]).getCharacter(); 
			                 ChosenCharacter.setTarget(ChosenTarget);
			           }
			        	   
			       }
			       
			       if (map[rowIndex][colIndex] instanceof CharacterCell && 
			          ((CharacterCell) map[rowIndex][colIndex]).getCharacter() instanceof Zombie) {
			               
			              Zombie zombie = (Zombie) ((CharacterCell) map[rowIndex][colIndex]).getCharacter();
			              ChosenTarget = zombie;          
			              ChosenCharacter.setTarget(ChosenTarget);       
			      }       
			    }      
			   text.setText(ChosenCharacter.InventoryToString());
			
			
            
			
	
				
			
			
			
			
		
		}
//		public void updateMove(Hero h) {
//			for(int i = 0; i <15;i++) {
//				for(int j = 0; j<15; j++) {
//					
//						r[i][j].setVisible(true);
//				if(r[i][j].isVisible()) {
//					r[i][j] = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GOLD);
//				}
//					
//				}
//				}
//			
//		
//		}
	
	
		public void updateMap() {
			
			if(Game.checkWin()) {
				for (int i = 0; i < 15; i++) {
				    for (int j = 0; j < 15; j++) {
				    	 if (map[i][j] instanceof CharacterCell) {
				               r[i][j].setFill(Color.GREEN);
				           	r[i][j].setVisible(true);
				           }  
				  
				else if(map[i][j] instanceof CollectibleCell) {
					r[i][j].setFill(Color.GREEN);
					r[i][j].setVisible(true);
				}
				else
					r[i][j].setFill(Color.GREEN);
				r[i][j].setVisible(true);
				  }
				    }
		
		}
		//NEED TO VIEW IT IN GAME THEN CLOSE
			
			
			else if(Game.checkGameOver()) {
				for(int i = 0; i<15; i++) {
					for (int j = 0; j < 15; j++) {
						  if ( (j == 0) || // Top horizontal line
						             (i == 14)) {  // Vertical line  
						          
						           if (map[i][j] instanceof CharacterCell) {
						               r[i][j].setFill(Color.RED);
						           	r[i][j].setVisible(true);
						           }  
						  
						else if(map[i][j] instanceof CollectibleCell) {
							r[i][j].setFill(Color.RED);
							r[i][j].setVisible(true);
						}
						else
							r[i][j].setFill(Color.RED);
						r[i][j].setVisible(true);
						  }
						  else {
							  if (map[i][j] instanceof CharacterCell) {
					               r[i][j].setFill(Color.WHITE);
					           	r[i][j].setVisible(true);
					           }  
					  
					else if(map[i][j] instanceof CollectibleCell) {
						r[i][j].setFill(Color.WHITE);
						r[i][j].setVisible(true);
					}
					else
						r[i][j].setFill(Color.WHITE);
					r[i][j].setVisible(true);
					  }
						  }
					
					}
				
			}
			else
			for(int i = 0; i<15; i++) {
				for (int j = 0; j < 15; j++) {
					
					if (map[i][j] instanceof CharacterCell) {
						if (((CharacterCell) map[i][j]).getCharacter() != null) {
							if (((CharacterCell) map[i][j]).getCharacter() instanceof Hero) {
							
								r[i][j].setFill(Color.BLUE);

								}

							 else {
								
										
								r[i][j].setFill(Color.GREEN);

							}
						} else {
							r[i][j].setFill(Color.GOLD);

						}
					} else if (map[i][j] instanceof CollectibleCell) {
						if (((CollectibleCell) map[i][j]).getCollectible() instanceof Vaccine) {
						
							r[i][j].setFill(Color.GREY);

						} else {
							
							r[i][j].setFill(Color.BROWN);

						}
					} else {
						r[i][j].setFill(Color.GOLD);
						
					}
					r[i][j].setVisible(map[i][j].isVisible());
				}
			}
		
		}

		

	public static void main(String[] args) {
		launch(args);
	}

}
