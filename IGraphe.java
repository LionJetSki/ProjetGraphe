
import java.util.ArrayList;
public interface IGraphe {
	int getNbSommets();
	void ajouterArc(int source, int valuation, int destination);
	int getValuation(int i, int j);
	boolean aArc(int i, int j);
  public ArrayList<Integer> getAdjacent(int  n1);
  public boolean estOK(int SDebut, int SFin);
}
