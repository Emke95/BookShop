package emma.store.entity;

public enum Category {

	Fiction("Ficton"), Romance ("Romance"), Fantasy("Fantasy"), Mystery ("Mystery"), NonFiction("NonFiction");

	String categoryName;

	Category(String categoryName) {

		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
