import java.util.Scanner;
import java.util.ArrayList;

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

class Project {

	private String name = null;
	private String subject = null;
	private int duration = -1;

	public Project() {}

	public void readProject() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Donnez le nom du projet : ");
		name = scanner.nextLine();
		System.out.println("Donnez le sujet du projet : ");
		subject = scanner.nextLine();
		System.out.println("Donnez la durée du projet : ");
		duration = scanner.nextInt();
	}

}

