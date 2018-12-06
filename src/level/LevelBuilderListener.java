package level;

public interface LevelBuilderListener {
	public void plantAdded(LevelEntityEvent e);
	public void zombieAdded(LevelEntityEvent e);
	public void updateWaves(LevelPointEvent e);
	public void updateInitialSunPoints(LevelPointEvent e);
}
