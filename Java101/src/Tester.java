
public class Tester 
{
	public static void main(String[] args)
	{
		Movie angryBirds = new Movie("Angry Birds: The Movie", 2016);
		Actor bob = new Actor("Bob", "Kid");
		Actor steve = new Actor("Steve", "Boy");
		Actor jim = new Actor("Jim", "Male Adolescent");
		//String[] listActors = new String[] {"Red", "The Blues", "Chuck", "Bomb", "Matilda", "Terence", "Bubbles", "Stella", "Hal", "The Pigs"};
		Actor[] listActors = new Actor[] {bob, steve, jim};
//		angryBirds.setPeopleAct(listActors);
		System.out.println(angryBirds.findActor("Kid"));
		System.out.println(angryBirds.findCharacter("Jim"));
		System.out.println(angryBirds.toString());
	//	angryBirds.removeActor(jim);
		System.out.println(angryBirds.findActor("Kid"));
		System.out.println(angryBirds.findCharacter("Jim"));
		System.out.println(angryBirds.toString());
		//angryBirds.addActor(jim);
		System.out.println(angryBirds.findActor("Kid"));
		System.out.println(angryBirds.findCharacter("Jim"));
		System.out.println(angryBirds.toString());
		
	}
}
