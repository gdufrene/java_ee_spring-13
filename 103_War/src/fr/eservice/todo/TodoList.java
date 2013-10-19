package fr.eservice.todo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


/**
 * Une liste de tâches à réaliser.
 * 
 * @author guillaume
 */
public class TodoList {
	
	private Set<Task> tasks = new HashSet<Task>();
	private Task[] taskArrayType = new Task[0]; 
	
	
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
	public Task[] getTasks(final ORDER order) {
		Task[] res = tasks.toArray( taskArrayType );
		Arrays.sort(res, new Comparator<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				ORDER ordering = order;
				if ( ordering == null ) ordering = ORDER.ADDED_DATE;
				long diff = 0;
				switch (ordering) {
				default:
				case ADDED_DATE:
					diff = t1.getAddedDate() - t2.getAddedDate();
					if ( diff == 0 ) diff = t1.getReference() - t2.getReference();
					break;

				case TARGET_DATE:
					diff = t1.getTargetDate() - t2.getTargetDate();
					break;
				}
				return diff > 0 ? 1 : (diff < 0 ? -1 : 0);
			}
		});
		return res;
	}
	
	private Task find( int id ) {
		for( Task t : tasks ) {
			if ( t.getReference() == id ) return t;
		}
		return null;
	}
	
	/**
	 * Ajoute une tâche à la liste.
	 * Si la tâche existe déjà dans la liste, elle est mise à jour.
	 * 
	 * @param task la tâche à ajouter ou mettre à jour.
	 */
	public void addTask(Task task) throws ParameterException {
		if ( task == null ) 
			throw new ParameterException("task", "task can't be null");
		removeTask(task.getReference());
		tasks.add( task );
	}

	/**
	 * Retire une tâche de la liste.
	 * @param taskRef l'identifiant de la tâche à supprimer.
	 */
	public void removeTask(int taskRef) {
		Task existing = find( taskRef );
		if ( existing != null ) tasks.remove( existing );
	}
	
	/**
	 * Note une tâche comme étant réalisée.
	 * 
	 * @param taskRef identifiant de la tâche
	 */
	public void completeTask(int taskRef) {
		Task t = find(taskRef);
		if ( t == null ) return;
		t.complete();
	}
}
