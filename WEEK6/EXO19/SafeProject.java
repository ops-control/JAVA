import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
class SafeProject {
	
	private final static int NB_PROJECTS = 3;
	
	public static void main(String[] args) {
		ArrayList<Project> projects = new ArrayList<Project>();
		
		do {
			Project project = new Project();
			project.readProject();
			projects.add(project);
		} while (projects.size() < NB_PROJECTS);
	}

}

class WrongDurationException extends Exception{
	public WrongDurationException(){
		super("Entiers SVP");
	}
	public WrongDurationException(String message){
		super(message);
	}
}

class NameTooLongException extends Exception{
	public NameTooLongException(){
		super("texte de plus 50 caracteres");
	}
	public NameTooLongException(String message){
		super(message);
	}
}

class Project {

	private String name = null;
	private String subject = null;
	private int duration = -1;

	public Project() {}

	public void readProject() {
		Scanner scanner = new Scanner(System.in);
		boolean test=false;
		while(!test){
			System.out.println("Donnez le nom du projet : ");
			try{
				name = scanner.nextLine();
				if(name.length()>50){
					throw new NameTooLongException();
				}
				test=true;
			}
			catch(NameTooLongException e){
				System.out.println(e.getMessage());
			}
		}	
		while(test){

			System.out.println("Donnez le sujet du projet : ");
			try{
				subject = scanner.nextLine();
				if(subject.length()>50){
					throw new NameTooLongException();
				}
				test=false;
			}
			catch(NameTooLongException e){
				System.out.println(e.getMessage());
			}
		}
		while(!test){
			System.out.println("Donnez la durée du projet : ");
			try{
				duration = scanner.nextInt();
				if(duration<0){
					throw new WrongDurationException();
				}
				test=true;
			}
			catch(WrongDurationException e){
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e){
				System.out.println("entrer un entier");
				scanner.next(); 
			}
		}
	}

}

