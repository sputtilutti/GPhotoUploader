# Google PhotoUploader

An attempt to build a solution to automate upload of photos (including organizing stuff in albums).

Picasa retired in 2016, and there is no easy way to upload categorized photo contents (photos in catalogs on computer).
So I figured I could build something myself based on [Picasa Web Album API](https://developers.google.com/picasa-web/docs/3.0/developers_guide_java) to achieve this.

Unfortunately, I realized that Google disabled all writing operations (create photo, create album, etc), so my grand ingenious plan failed...
The code can serve as example to authenticate with google api, and be used to do read-only operations to photos api.

# Authenticating API

The sample code authenticates with OAuth2, you need to create a `client_secret.json` file that contains metadata about your project (and of course, you need to register your project on your developers.google account). 

This project started out as a Google Drive integration, so I followed [these instructions](https://developers.google.com/drive/v3/web/quickstart/python) to get the secret file:

 * Use this wizard to create or select a project in the Google Developers Console and automatically turn on the API. Click Continue, then Go to credentials.
 * On the Add credentials to your project page, click the Cancel button.
 * At the top of the page, select the OAuth consent screen tab. Select an Email address, enter a Product name if not already set, and click the Save button.
 * Select the Credentials tab, click the Create credentials button and select OAuth client ID.
 * Select the application type Other, enter the name "Drive API Quickstart", and click the Create button.
 * Click OK to dismiss the resulting dialog.
 * Click the file_download (Download JSON) button to the right of the client ID.
 * Move this file to your working directory and rename it client_secret.json. 