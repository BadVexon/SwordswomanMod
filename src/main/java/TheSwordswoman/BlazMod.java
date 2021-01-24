package TheSwordswoman;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import TheSwordswoman.cards.AbstractBlazCard;
import TheSwordswoman.events.Grindstone;
import TheSwordswoman.events.LuckyBox;
import TheSwordswoman.events.PurpleButterfly;
import TheSwordswoman.relics.AbstractBlazRelic;
import TheSwordswoman.stances.DawnflyStance;
import TheSwordswoman.util.SecondDamage;
import TheSwordswoman.util.SillyVariable;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class BlazMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        OnStartBattleSubscriber,
        PostUpdateSubscriber,
        PostInitializeSubscriber {
    public static final String SHOULDER1 = "blazmodResources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = "blazmodResources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = "blazmodResources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = "blazmodResources/images/512/attack.png";
    private static final String SKILL_S_ART = "blazmodResources/images/512/skill.png";
    private static final String POWER_S_ART = "blazmodResources/images/512/power.png";
    private static final String CARD_ENERGY_S = "blazmodResources/images/512/energy.png";
    private static final String TEXT_ENERGY = "blazmodResources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = "blazmodResources/images/1024/attack.png";
    private static final String SKILL_L_ART = "blazmodResources/images/1024/skill.png";
    private static final String POWER_L_ART = "blazmodResources/images/1024/power.png";
    private static final String CARD_ENERGY_L = "blazmodResources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = "blazmodResources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = "blazmodResources/images/charSelect/charBG.png";
    private static String modID;
    private static String artifactID;

    public static boolean choosingUpgradeCard;
    public static boolean choosingRemoveCard;

    public static Color todoColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); //TODO: Set this to your character's favorite color!

    @SpireEnum
    public static AbstractCard.CardTags TRANCE;

    public BlazMod() {

        BaseMod.subscribe(this);

        modID = "blazmod"; //TODO: Change this!
        artifactID = "TheSwordswoman"; //TODO: Change this, but make sure it matches the ArtifactID in your pom.

        BaseMod.addColor(TheSwordswoman.Enums.SWORDSWOMAN_COLOR, todoColor, todoColor, todoColor,
                todoColor, todoColor, todoColor, todoColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);

    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent(PurpleButterfly.ID, PurpleButterfly.class, Exordium.ID);
        BaseMod.addEvent(LuckyBox.ID, LuckyBox.class, TheCity.ID);
        BaseMod.addEvent(Grindstone.ID, Grindstone.class, TheBeyond.ID);
    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        BlazMod todoMod = new BlazMod();
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheSwordswoman("the Swordswoman", TheSwordswoman.Enums.THE_SWORDSWOMAN),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheSwordswoman.Enums.THE_SWORDSWOMAN);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(artifactID)
                .packageFilter(AbstractBlazRelic.class)
                .any(AbstractBlazRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof TheSwordswoman) {
            AbstractDungeon.player.stance = new DawnflyStance();
        }
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SillyVariable());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(artifactID)
                .packageFilter(AbstractBlazCard.class)
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/Charstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostUpdate() {
        if (choosingUpgradeCard && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));// 54
            card.upgrade();
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy()));// 59
            choosingUpgradeCard = false;
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        if (choosingRemoveCard && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            card.untip();// 73
            card.unhover();// 74
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, (float) Settings.WIDTH / 2, (float) Settings.HEIGHT / 2.0F));// 75
            AbstractDungeon.player.masterDeck.removeCard(card);// 78
            choosingRemoveCard = false;
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }
}
