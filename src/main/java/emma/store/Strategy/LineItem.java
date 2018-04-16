package emma.store.Strategy;

public class LineItem {
	private String description;
	private int costInEuro;
	public LineItem(String description, int costInEuro) {
		this.description = description;
		this.costInEuro = costInEuro;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCostInEuro() {
		return costInEuro;
	}
	public void setCostInEuro(int costInEuro) {
		this.costInEuro = costInEuro;
	}
}
