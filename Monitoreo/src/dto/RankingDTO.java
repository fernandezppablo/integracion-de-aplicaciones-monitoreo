package dto;

import java.util.List;

public class RankingDTO  {
	private List<ItemRankingDTO> rankings;
	
	
	
	public RankingDTO(List<ItemRankingDTO> ranks) {
		super();
		this.rankings = ranks;
	}

	public List<ItemRankingDTO> getRankings() {
		return rankings;
	}

	public void setRanks(List<ItemRankingDTO> setRankings) {
		this.rankings = rankings;
	}
	
	
}

