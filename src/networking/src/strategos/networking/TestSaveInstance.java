package strategos.networking;

import strategos.GameCollections;
import strategos.SaveInstance;
import strategos.UnitOwner;

import java.util.ArrayList;

/**
 * An implementation of SaveInstance used only for testing.
 */
public class TestSaveInstance implements SaveInstance {
	int i;
	String s;

	public TestSaveInstance(String s, int i) {
		this.s = s;
		this.i = i;
	}

	//All methods from SaveInstance can return null as they are not used in this implementation

	@Override
	public GameCollections getWorld() {
		return null;
	}

	@Override
	public ArrayList<UnitOwner> getPlayers() {
		return null;
	}

	@Override
	public UnitOwner getTurn() {
		return null;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;

		TestSaveInstance that = (TestSaveInstance) other;

		return i == that.i && (s != null ? s.equals(that.s) : that.s == null);
	}

	@Override
	public int hashCode() {
		int result = i;
		result = 31 * result + (s != null ? s.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "TestSaveInstance{" +
				"i=" + i +
				", s='" + s + '\'' +
				'}';
	}
}
