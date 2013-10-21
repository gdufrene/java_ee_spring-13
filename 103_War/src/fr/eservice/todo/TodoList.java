package fr.eservice.todo;


/**
 * Une liste de tâches à réaliser.
 * 
 * @author guillaume
 */
public class TodoList {
	
	/**
	 * Ordre de tri des tâches.
	 */
	public enum ORDER {
		/**
		 * Par date d'ajout
		 */
		ADDED_DATE,
		/**
		 * Par date d'échéance
		 */
		TARGET_DATE
	}
	
	/**
	 * Liste toutes les tâches d'une TodoList.
	 * @param order tri choisi pour l'ordre des tâches retournées
	 * @return la liste des tâches triées, éventuellement vide.
	 */
	public Task[] getTasks(ORDER order) {
		return null;
	}
	
	/**
	 * Ajoute une tâche à la liste.
	 * Si la tâche existe déjà dans la liste, elle est mise à jour.
	 * 
	 * @param task la tâche à ajouter ou mettre à jour.
	 */
	public void addTask(Task task) {
		
	}

	/**
	 * Retire une tâche de la liste.
	 * @param taskRef l'identifiant de la tâche à supprimer.
	 */
	public void removeTask(int taskRef) {
		
	}
	
	/**
	 * Note une tâche comme étant réalisée.
	 * 
	 * @param taskRef identifiant de la tâche
	 */
	public void completeTask(int taskRef) {
		
	}
}
