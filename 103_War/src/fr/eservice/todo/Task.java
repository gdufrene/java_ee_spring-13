package fr.eservice.todo;

/**
 * Une tâche à réaliser.<br/>
 * Cette tâche devrait posséder :<ul>
 *   <li>une référence (identifiant unique)</li>
 *   <li>un titre court</li>
 *   <li>une description</li>
 *   <li>une date d'ajout</li>
 *   <li>une date limite de réalisation</li>
 *   <li>une propriété pour savoir si oui ou non elle a été réalisée</li>
 * </ul>
 * 
 * @author guillaume
 */
public class Task {
	
	private static int nextReference = 1;

	private int reference;
	private String 
		title,
		description;
	private long
		addedDate,
		targetDate;
	private boolean completed;
	
	public Task() {
		addedDate = System.currentTimeMillis() / 1000L;
		reference = nextReference++;
	}

	
	public int getReference() {
		return reference;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public long getAddedDate() {
		return addedDate;
	}
	
	public long getTargetDate() {
		return targetDate;
	}
	
	public boolean hasBeenCompleted() {
		return completed;
	}
	
	/**
	 * Une méthode pour créer une nouvelle tâche.
	 * 
	 * @param title l'intitullé de la tache.
	 * @param description la description de la tache.
	 * @param target la date limite de réalisation.
	 * 
	 * @return une nouvelle tâche à réaliser.
	 * 
	 * @throws ParameterException
	 */
	public static Task create(String title, String description, Long target) 
	throws ParameterException
	{
		if ( title == null ) throw new ParameterException("title", "le titre doit être renseigné.");
		title = title.trim();
		if ( title.isEmpty() ) throw new ParameterException("title", "le titre doit être renseigné.");
		
		if ( description == null ) description = "";
		
		Task task = new Task();
		task.title = title;
		task.description = description;
		if ( target != null ) task.targetDate = target;
		return task;
	}


	public void complete() {
		this.completed = true;
	}


	
}
