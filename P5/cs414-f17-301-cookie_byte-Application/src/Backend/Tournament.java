package Backend;
import java.util.*;

import Database.DatabaseManagerImpl;
import Database.UsersJavaObject;

public class Tournament {

	//4 users involved in the tournament
	User player1;
	User player2;
	User player3;
	User player4;
	
	//List that will create random matches
	List <User> matches= new ArrayList <User>();;
	
	//Tracks the state of the Tournament 
	TournamentStatus status;
	
	//Tracks the state of the invitations that were sent.
	List <Invite> inviteList = new ArrayList<Invite>();
	
	//Tracks the winner
	User winner;
	
	//Initializes the games and the corresponding ID's that will be played in the tournament.
	GameController SemiFinal_1; int GameID1;
	GameController SemiFinal_2; int GameID2;
	GameController Championship; int GameID3;
	
		public Tournament (int GameID_1, int GameID_2, int GameID_3)
		{
			this.GameID1 = GameID_1;
			this.GameID2 = GameID_2;
			this.GameID3 = GameID_3;
			status = TournamentStatus.PENDING;
		}
		
	//Invites all users the tournament starts once all invitations are responded to.
	
		public void TournamentInvite(User to1, User to2, User to3, User from){
			
			//Sets player1
			this.player1 = from;
			
			//Sets and sends an invitation for player 2
			this.player2= to1;
			Invite invitation1 = new Invite (to1, from, "PENDING", this);
			invitation1.setTournament(true);
			
			//Sets and sends an invitation for player 3
			this.player3= to2;
			Invite invitation2 = new Invite (to2, from, "PENDING",this);
			invitation2.setTournament(true);
			
			//Sets and sends an invitation for player 4
			this.player4= to3;
			Invite invitation3 = new Invite (to3, from, "PENDING",this);
			invitation3.setTournament(true);
			
			//adds the invites set to the Tournament class
			inviteList.add(invitation1);
			inviteList.add(invitation2);
			inviteList.add(invitation3);
			
		}
		
		public void checkTournamentStatus (){
			int RequestAccepted = 0;
			
			// if the status of the Tournament is Pending check all invitations
			if (status == TournamentStatus.PENDING){
				for (Invite i : inviteList){
					//If the invitation is accecpted
					if (i.status == InvitationStatus.ACCECPTED){
						RequestAccepted++;
						System.out.println("1 Tournament Invite Accecpted waiting on.... " + (3-RequestAccepted) +" more!");
					}
					//removes all invites form other players if one is declined
					else if (i.status == InvitationStatus.DECLINED){
						player2.removeInvite(inviteList.get(0));
						player3.removeInvite(inviteList.get(1));
						player4.removeInvite(inviteList.get(2));
						setStatus(TournamentStatus.FINISHED);
						System.out.print("Tournament Invite Declined");
						break;
					}
					// if pending the loop just continues
					else{
						System.out.println("The Invite of "+ i.userTo.toString() + " is still Pending." );
						continue;
					}	
				}
				//
				if(RequestAccepted == 3){
					//Tournament is automatically started here
					setStatus(TournamentStatus.ONGOING);
					startTournament();
				}
				
			}
		}
		
		public void startTournament (){
			System.out.println("Tournament Started");
			
			//Add the starting players to be matched up
			matches.add(player1);
			matches.add(player2);
			matches.add(player3);
			matches.add(player4);
			 
			//Shuffles the players randomly to be paired.
			Collections.shuffle(matches);
			
			//makes semifinal Matches ***Need to find unique game id from database to pass here***
			SemiFinal_1 = new GameController(GameID1 , matches.get(0), matches.get(1));
			SemiFinal_1.setTournament(true);
			SemiFinal_1.setTournament(this);
			SemiFinal_1.setStatus(GameStatus.ACTIVE);
			
			//Now done in database.
			//matches.get(0).addCurrentGame(SemiFinal_1);
			//matches.get(1).addCurrentGame(SemiFinal_1);
			
			SemiFinal_2 = new GameController(GameID2 ,matches.get(2), matches.get(3));
			SemiFinal_2.setTournament(true);
			SemiFinal_2.setTournament(this);
			SemiFinal_2.setStatus(GameStatus.ACTIVE);
			
			//Now done in database.
			//matches.get(2).addCurrentGame(SemiFinal_2);
			//matches.get(3).addCurrentGame(SemiFinal_2);
		}
		
		//A check after the winconditions of each move to see if a championship 
		public void checkNextRound(){
			
			// Sets up the Championship game with the winners of the two previous games.
			if (SemiFinal_1.status == GameStatus.FINISHED && SemiFinal_2.status == GameStatus.FINISHED){
				Championship = new GameController(GameID3, SemiFinal_1.getWinner(), SemiFinal_2.getWinner());
				Championship.setTournament(false);
				Championship.setTournament(this);
				System.out.println("Semifinals are finished... championship game started!!");
			}
		}
		
		public void checkChampion(){
			if (Championship.status == GameStatus.FINISHED){
				this.setStatus(TournamentStatus.FINISHED);
				this.winner = Championship.winner;
				System.out.println("Tournament is over!! "+ winner.userID + "is the winner!");
			}
		}
		

		public List<User> getMatches() {
			return matches;
		}

		public void setMatches(List<User> matches) {
			this.matches = matches;
		}

		public TournamentStatus getStatus() {
			return status;
		}

		public void setStatus(TournamentStatus status) {
			this.status = status;
		}

		public List<Invite> getInviteList() {
			return inviteList;
		}

		public void setInviteList(List<Invite> inviteList) {
			this.inviteList = inviteList;
		}

		public User getWinner() {
			return winner;
		}

		public void setWinner(User winner) {
			this.winner = winner;
		}
	
}
