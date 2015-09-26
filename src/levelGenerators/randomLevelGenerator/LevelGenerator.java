package levelGenerators.randomLevelGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import core.game.GameDescription;
import core.game.GameDescription.SpriteData;
import core.generator.AbstractLevelGenerator;
import tools.ElapsedCpuTimer;

public class LevelGenerator extends AbstractLevelGenerator{

	private Random random;
	
	public LevelGenerator(GameDescription game, ElapsedCpuTimer elapsedTimer){
		random = new Random();
	}
	
	@Override
	public String GenerateLevel(GameDescription game, ElapsedCpuTimer elapsedTimer) {
		String result = "";
		HashMap<Character, ArrayList<String>> levelMapping = game.getLevelMapping();
		ArrayList<SpriteData> sprites = game.getAllSpriteData();
		String avatarName = game.getAvatar().name;
		
		int length = (int)(sprites.size() * (1 + random.nextDouble() / 2) * 8);
		int width = (int)(sprites.size() * (1 + random.nextDouble() / 2) * 10);
		
		Character avatar = 'a';
		int avatarX = random.nextInt(width);
		int avatarY = random.nextInt(length);
		
		ArrayList<Character> choices = new ArrayList<Character>();
		for(Map.Entry<Character, ArrayList<String>> pair:levelMapping.entrySet()){
			choices.add(' ');
			choices.add(' ');
			choices.add(' ');
			choices.add(' ');
			
			if(!pair.getValue().contains(avatarName)){
				choices.add(pair.getKey());
			}
			else{
				avatar = pair.getKey();
			}
		}
		
		for(int y=0; y < length; y++){
			for(int x=0; x < width; x++){
				if(x == avatarX && y == avatarY){
					result += avatar;
				}
				else
				{
					result += choices.get(random.nextInt(choices.size()));
				}
			}
			result += "\n";
		}
		
		return result;
	}

}
