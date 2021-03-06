package vexatos.beespecific;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import hardcorequesting.quests.ItemPrecision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vexatos.beespecific.precision.SpeciesSpecificPrecisionType;
import vexatos.beespecific.precision.PrecisionModes.Alleles;
import vexatos.beespecific.precision.PrecisionModes.Type;
import vexatos.beespecific.reference.Mods;

/**
 * @author Vexatos
 */
@Mod(modid = Mods.BeeSpecific, name = Mods.BeeSpecific_NAME, version = "@VERSION@",
	dependencies = "required-after:" + Mods.HQM + "@[4.3.0.1,);required-after:" + Mods.Forestry + "@[4.0.9,)")
public class BeeSpecific {

	@Instance(Mods.BeeSpecific)
	public static BeeSpecific instance;

	public static Logger log;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		log = LogManager.getLogger(Mods.BeeSpecific);
		registerBeeSpecificPrecisionType("Species - both alleles same", Type.ANY, Alleles.BOTH);
		registerBeeSpecificPrecisionType("Type & Species - both alleles same", Type.MATCH, Alleles.BOTH);
		registerBeeSpecificPrecisionType("Species - only active allele", Type.ANY, Alleles.ACTIVE);
		registerBeeSpecificPrecisionType("Type & Species - only active allele", Type.MATCH, Alleles.ACTIVE);
	}

	public void registerBeeSpecificPrecisionType(String name, Type type, Alleles allele) {
		ItemPrecision.registerPrecisionType(String.format("SPECIES_%s_%s", type, allele), new SpeciesSpecificPrecisionType(name, type, allele));
	}
}
