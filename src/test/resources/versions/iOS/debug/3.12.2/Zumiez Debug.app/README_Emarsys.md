#  Emarsys usage in Zumiez App 

The code is associated with interacting with the Emarsys API for various purposes, such as tracking, recommendations, and errors handling.

## Current Usage

### 1. **EmarsysManager Struct**

    Tracks user behaviors, custom events, and manages contacts.

   - **SetupWithConfig:**
     - Initializes Emarsys with a given configuration.
     
   - **SetContact:**
     - Sets the contact information in Emarsys with given contactFieldId and contactFieldValue.
     - Used internally within the `setUser` function.

   - **TrackCustomEvent:**
     - Allows tracking custom events in the Emarsys platform.
     - It's a wrapper around the `Emarsys.trackCustomEvent` method.

   - **Predict Tracking:**
     - Methods to track item views, cart contents, and purchases using Emarsys Predict.

   - **PerformCardAction:**
     - Handles actions performed on cards, and tracks events related to activities, rewards, and products.

   - **SendViewEvent:**
     - Sends a view event to Emarsys, including item ID and optional campaign information.

### 2. **EmarsysAPI Class**

   Identifies users, retrieves product recommendations, and handles setup.

   - **setUser:**
     - Sets the user in Emarsys using a hashed email address.
     - Triggers an app open event if the user is set successfully.

   - **Product Retrieval Methods:**
     - Several methods (`getByCart`, `getByRelated`, `getByCategory`, etc.) to retrieve products based on different criteria like cart contents, related items, or categories from Emarsys.

   - **Shared Instance:**
     - Uses the singleton pattern to provide a shared instance for accessing the Emarsys functionalities.
     
    #### 2.1. **CartItemModel Class**

       - A model class that conforms to `EmarsysCartItemProtocol`.
       - Represents items in the cart, holding information like item ID, price, and quantity.

    #### 2.2. **EmarsysCardsError Enum**

       - Enumerates potential errors encountered when trying to load products via Emarsys.
       - Includes cases for errors related to cart, related items, categories, and also bought items.
       - Provides human-readable error descriptions.
     
### 3. **EmarsysEvents Class**

    Tracks app open events and card interactions.

   - **sendAppOpen:**
     - A utility method to easily track when the app is opened.

   - **SendEmarsysViewEvent & PerformCardAction:**
     - Methods to handle various interactions with cards (activities, rewards, products) and send appropriate tracking events to Emarsys.
     
   
## Summary of Usage

    ### Initialization
        - `EmarsysManager.setup`: used in `AppDelegate`
        - `EmarsysAPI.setUser`: used in `SignedInContainerViewController.viewDidLoad` (uses `setContact`)
        
    ### Sending Data To Emarsys:
        - `EmarsysManager.trackCustomEvent`: 
            - used in `EmarsysEvents` (app_open) 
            - and `HomeViewController` (view_product)
        - `EmarsysManager.predictTrackItem`: 
            - used in `EmarsysEvents` as a part of functions that send events or perform card actions
        - `EmarsysManager.predictTrackCart`: 
            - used in `CommerceDetailsViewController` 
            - and `CardDetailsViewController`
        - `EmarsysManager.predictTrackPurchase`: 
            - used in `CommerceDetailsViewController`
        - `EmarsysManager.performCardAction`;
            - used in `ActivityViewModel`
            - and `RewardsGroupViewModel`
    
    ### Receiving Data from Emarsys
        - `EmarsysManager.predictRecommendProducts`: used in `EmarsysAPI` as a proxy to fetch items from `Emarsys` (retrieve product recommendations):
            - Used in `CampaignTableRepository` and `CommerceRepository`:
                - `EmarsysAPI.shared.getByCart(items:`
                - `EmarsysAPI.shared.getByCart(filters:` 
                - `EmarsysAPI.shared.getByRelated`
                - `EmarsysAPI.shared.getByCategory`
                - `EmarsysAPI.shared.getByAlsoBought`
                - `EmarsysAPI.shared.getByPersonal`
                
                
## Emarsys Usage Essence:

    ### Initialization
    - Setup
        - has Config
        - called once (in AppDelegate)
    - User Data Authentication:
        - sends user contact data
        - called after logging in
    
    ### App is Sending Data:
        - sends a custom event, along with optional attributes, to the `Emarsys platform for analytics`
        - sends item tracking data to `Emarsys Predict`
        - sends purchase tracking data to `Emarsys Predict`
    
    ### App is Receiving Data:
        - retrieves a list of recommended products by utilizing `Emarsys Predict`’s recommendation engine.


## Sitecore (formerly Reflektions)
     
     Link to JIRA ticket:
     https://zumiez.atlassian.net/browse/HTH-1709
     
     Link to the documentation:
     https://doc.sitecore.com/discover/en/developers/discover-developer-guide/integrate-using-discover-rest-apis.html
     


