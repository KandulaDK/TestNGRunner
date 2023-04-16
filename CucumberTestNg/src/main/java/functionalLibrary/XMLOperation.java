package functionalLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aventstack.extentreports.ExtentTest;


public class XMLOperation extends ReusableAction {
	
	public XMLOperation(WebDriver webDriver, ExtentTest test) {
		super(webDriver, test);
	}

	public XMLOperation() {	}

	private static Logger objLgr = LogManager.getLogger(XMLOperation.class.getName());

	public HashMap<String, String> updateXML (String path, String[] fields, String setValue) {
		HashMap<String, String> data = new HashMap<String, String>();
		Document doc = null;
		try {
			FileInputStream fileInStr = new FileInputStream(new File(path));
			DocumentBuilderFactory docBuilderFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFac.newDocumentBuilder();
			doc = docBuilder.parse(fileInStr);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}

		data.put("NoOfIterations", String.valueOf(doc.getElementsByTagName("ItemName").getLength()));
		for (String field : fields) {
			String mapValue = "";
			if (setValue == null) {
				String value = "";
				int len = getFieldLength(field);
				String fieldName = getFieldName(field);

				NodeList nodeList = doc.getElementsByTagName(field);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (len > 0)
						value = fieldName + generateRandomNumber(len);
					node.setTextContent(value);
					if(mapValue.equals(""))
						mapValue = value;
					else
						mapValue = mapValue + "," + value;
				}
				data.put(field, mapValue);
			}
			else {
				NodeList nodeList = doc.getElementsByTagName(field);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					node.setTextContent(setValue);
				}
				data.put(field, setValue);
			}
		}

		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void updateXMLMultipleTags (String path, String parentTag, String conditionTag, String conditionValue, String setTag, String setValue) {
		Document doc = null;
		try {
			FileInputStream fileInStr = new FileInputStream(new File(path));
			DocumentBuilderFactory docBuilderFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFac.newDocumentBuilder();
			doc = docBuilder.parse(fileInStr);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Boolean updateXMLFlag = false;
		NodeList nodeList = doc.getElementsByTagName(parentTag);
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (updateXMLFlag)
				break;
			else {
				NodeList conditionNodeList = nodeList.item(i).getChildNodes();
				for (int j = 0; j < conditionNodeList.getLength(); j++) {
					if (conditionNodeList.item(j).getNodeName().equals(conditionTag) && conditionNodeList.item(j).getTextContent().equals(conditionValue)) {
						if (conditionNodeList.item(j + 2).getNodeName().equals(setTag)) {
							conditionNodeList.item(j + 2).setTextContent(setValue);
							updateXMLFlag = true;
							break;
						}
					}
				}
			}
		}

		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public int getFieldLength (String field) {
		int length = 0;
		switch (field) {
		case "ASNID":
			length = 7;
			break;
		case "LPNID" :
			length = 17;
			break;
		case "OrderId" :
			length = 7;
			break;
		case "PurchaseOrderID" :
			length = 7;
			break;
		case "DistributionOrderId" :
			length = 7;
			break;
		case "Reference_ID" :
			length = 6;
			break;
		case "BillOfLadingNumber" :
			length = 13;
			break;
		case "ExternalSystemPurchaseOrderNbr":
			length = 7;
			break;
		default:
			length = 6;
			break;
		}
		return length;
	}

	public String getFieldName (String field) {
		String fieldName = "";
		switch (field) {
		case "ASNID":
			fieldName = "AUTO";
			break;
		case "LPNID" :
			fieldName = "LPN";
			break;
		case "OrderId" :
			fieldName = "AUTOPO";
			break;
		case "PurchaseOrderID" :
			fieldName = "AUTOPO";
			break;
		case "DistributionOrderId" :
			fieldName = "DOAUTO";
			break;
		case "ExternalSystemPurchaseOrderNbr" :
			fieldName = "BNAUTO";
			break;
		default:
			fieldName = "REFAUTO";
			break;
		}
		return fieldName;
	}

	public String updateItemXML(String xmlFilePath, String[] fields, List<HashMap<String, String>> dbData) {
		Document doc = null;
		String noOfIterations = "";
		try {
			FileInputStream fileInStr = new FileInputStream(new File(xmlFilePath));
			DocumentBuilderFactory docBuilderFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFac.newDocumentBuilder();
			doc = docBuilder.parse(fileInStr);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String field : fields) {
			String value = "";
			NodeList nodeList = doc.getElementsByTagName(field);
			switch(field) {
			case "ItemName":
				noOfIterations = String.valueOf(nodeList.getLength());
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size())
						value = dbData.get(i).get("ITEM_NAME");
					else
						value = dbData.get(0).get("ITEM_NAME");
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			case "ShippedQty":
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size())
						value = dbData.get(i).get("QUANTITY");
					else
						value = dbData.get(0).get("QUANTITY");
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			case "ShippedAsnQuantity":
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size())
						value = dbData.get(i).get("QUANTITY");
					else
						value = dbData.get(0).get("QUANTITY");
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			case "Quantity":
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size())
						value = dbData.get(i).get("QUANTITY");
					else
						value = dbData.get(0).get("QUANTITY");
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			case "OrderQty":
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size()) {
						if(dbData.get(i).get("ORDER_QTY") == null) {
							value = dbData.get(i).get("QUANTITY");
						}
						else {
							value = dbData.get(i).get("ORDER_QTY");
						}
					}
					else {
						if(dbData.get(0).get("ORDER_QTY") == null) {
							value = dbData.get(0).get("QUANTITY");
						}
						else {
							value = dbData.get(0).get("ORDER_QTY");
						}
					}
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			case "StandardPackQuantity":
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size())
						value = dbData.get(i).get("PACK_QUANTITY");
					else
						value = dbData.get(0).get("PACK_QUANTITY");
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			case "QtyUOM":
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size()) {
						if(dbData.get(i).get("PACKAGE_UOM_ID").equalsIgnoreCase("50"))
							value = "Unit";
						else if(dbData.get(i).get("PACKAGE_UOM_ID").equalsIgnoreCase("45"))
							value = "Cases";
						else if(dbData.get(i).get("PACKAGE_UOM_ID").equalsIgnoreCase("46"))
							value = "Packs";
					}
					else {
						if(dbData.get(0).get("PACKAGE_UOM_ID").equalsIgnoreCase("50"))
							value = "Unit";
						else if(dbData.get(0).get("PACKAGE_UOM_ID").equalsIgnoreCase("45"))
							value = "Cases";
						else if(dbData.get(0).get("PACKAGE_UOM_ID").equalsIgnoreCase("46"))
							value = "Packs";
					}
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			case "QuantityUOM":
				for(int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.getLength() == dbData.size()) {
						if(dbData.get(i).get("PACKAGE_UOM_ID").equalsIgnoreCase("50"))
							value = "Unit";
						else if(dbData.get(i).get("PACKAGE_UOM_ID").equalsIgnoreCase("45"))
							value = "Cases";
					}
					else {
						if(dbData.get(0).get("PACKAGE_UOM_ID").equalsIgnoreCase("50"))
							value = "Unit";
						else if(dbData.get(0).get("PACKAGE_UOM_ID").equalsIgnoreCase("45"))
							value = "Cases";
					}
					Node node = nodeList.item(i);
					node.setTextContent(value);
				}
				break;
			}
		}
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFilePath));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return noOfIterations;
	}

	public Document createXMLSummary() {
		Document doc = null;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setNamespaceAware(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();

			Element root = doc.createElement("batchrun");
			doc.appendChild(root);
			Node pi = doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"summary.xsl\"");
			doc.insertBefore(pi, root);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		return doc;
	}

	public Document createXMLTestCase() {
		Document doc = null;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setNamespaceAware(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		return doc;
	}

	public Element createTestCaseRootElement(Document document) {
		Element root = document.createElement("batchrun");
		document.appendChild(root);
		Node pi = document.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"tctemplate.xsl\"");
		document.insertBefore(pi, root);

		return root;
	}

	public Element createChildElement(Document document, Element parent, String childElementName, String[] attrArray, String[] childNodeArray) {
		Element child = document.createElement(childElementName);
		for(int i = 0; i < attrArray.length; i++) {
			child.setAttribute(attrArray[i].split(":")[0], attrArray[i].split(":")[1]);
		}

		for(int i = 0; i < childNodeArray.length; i++) {
			Element childElement = document.createElement(childNodeArray[i].split(":")[0]);
			if(childNodeArray[i].split(":").length == 1)
				childElement.appendChild(document.createTextNode(""));
			else
				childElement.appendChild(document.createTextNode(childNodeArray[i].split(":")[1]));
			child.appendChild(childElement);
		}
		parent.appendChild(child);	
		return child;
	}

	public void writeXMLFile(Document doc, String path) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
			objLgr.info("File saved!");
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public Element addXMLNodes(Document document, Element testCase, Element module, String attrArrayName, String activityName, String valueOfSelection, String screenshotPath, String status) throws  IOException {
		String[] attrArray = new String[] {"name:" + attrArrayName};
		if(module == null)
			module = createChildElement(document, testCase, "module", attrArray, new String[] {});

		String[] attrArrayStep = new String[] {"activity:" + activityName, "screenshot:" + screenshotPath};
		String[] childNodeArray = null;
		if(status == null)
			childNodeArray = new String[] {"activitytype:Information", "valueofselection:" + valueOfSelection, "validate:Not Applicable", "status:Not Applicable"};
		else if(status.equalsIgnoreCase("Pass"))
			childNodeArray = new String[] {"activitytype:Validation", "valueofselection:" + valueOfSelection, "validate:Successful", "status:" + status};
		else if(status.equalsIgnoreCase("Fail"))
			childNodeArray = new String[] {"activitytype:Validation", "valueofselection:" + valueOfSelection, "validate:Failed", "status:" + status};
		else
			childNodeArray = new String[] {"activitytype:Input", "valueofselection:" + valueOfSelection, "", ""};
		@SuppressWarnings("unused")
		Element step = createChildElement(document, module, "step", attrArrayStep, childNodeArray);
		return module;
	}

	public List<HashMap<String, String>> updateItemDBData(ReusableAction reusableAction, String xmlFilePath, List<HashMap<String, String>> itemData) {
		Document doc = null;
		List<HashMap<String, String>> updatedItemData = new ArrayList<HashMap<String, String>>();
		try {
			FileInputStream fileInStr = new FileInputStream(new File(xmlFilePath));
			DocumentBuilderFactory docBuilderFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFac.newDocumentBuilder();
			doc = docBuilder.parse(fileInStr);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		NodeList nodeList = doc.getElementsByTagName("ItemName");
		if(nodeList.getLength() == itemData.size()) {
			updatedItemData = itemData;
		}
		else {
			String itemName = itemData.get(0).get("ITEM_NAME");
			String itemBarcode = itemData.get(0).get("ITEM_BAR_CODE");
			String orderQuantity = itemData.get(0).get("QUANTITY");
			String packQuantity = itemData.get(0).get("PACK_QUANTITY");
			for(int i = 0; i < nodeList.getLength() - 1; i++) {
				HashMap<String, String> item = new HashMap<String, String>();
				String lineItemQuantity = String.valueOf(reusableAction.getRandomNumber(1, (Integer.parseInt(orderQuantity) + i - nodeList.getLength())));
				orderQuantity = String.valueOf(Integer.parseInt(orderQuantity) - Integer.parseInt(lineItemQuantity));
				item.put("ITEM_NAME", itemName);
				item.put("ITEM_BAR_CODE", itemBarcode);
				item.put("PACK_QUANTITY", packQuantity);
				item.put("QUANTITY", lineItemQuantity);
				updatedItemData.add(item);
			}
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("ITEM_NAME", itemName);
			item.put("PACK_QUANTITY", packQuantity);
			item.put("QUANTITY", orderQuantity);
			updatedItemData.add(item);
		}
		return updatedItemData;
	}

	public HashMap<String, String> convertToHashMap(String[] data, String separator) {
		HashMap<String,String> map = new HashMap<String,String>();
		for (String value : data) {
			String key = value.split(separator)[0];
			String mapValue = value.split(separator)[1];
			map.put(key, mapValue);
		}
		return map;
	}

	public static void main(String[] args) {
		XMLOperation xmlOperation = new XMLOperation();
		xmlOperation.createXMLSummary();
	}
}
