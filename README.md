dycon
=====

Grails Plugin for simple content management

The plugin that allows very simple Content management for pages without requiring a full CMS.
Add the following configuration to your application:

dycon { <br/>
        imageUploadDirectory = '/path_to_image_store'<br/>
        imageWebPath = '/path_to_image_url'<br/>
        previewDomain = 'preview.yoursite.com' //a url for serving un-published content<br/>
      }

Once the plugin is included in your project you can navigate to the content management pages at the url http://www.yoursite.com/dycon (access to this url should be restricted to the outside world).

Integrate content into your pages using the following tags: -

At the top of any content managed pages:  ```<dycon:page name="pageName"/>```

To include content managed text: ```<dycon:content name="contentName" default="lorem ipsum"/>```

To include content managed images: ```<dycon:image name="imageName" default="defaultImageLocation"/>```



