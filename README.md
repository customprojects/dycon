dycon
=====

Grails Plugin for simple content management

The plugin allows very simple Content management for pages without requiring a full CMS. When first run, 
the plugin will create tables to manage pages, content and images for an application.

Add the following configuration to your application:

```
dycon { 
        imageUploadDirectory = '/path_to_image_store'<br/>
        imageWebPath = '/path_to_image_url'<br/>
        previewDomain = 'preview.yoursite.com' //a url for serving un-published content
      }
```
Once the plugin is included in your project you can navigate to the content management pages at the url http://www.yoursite.com/dycon. 
Access to this url and the preview url configure above should be restricted from outside of your organisation.

Integrate content into your pages using the following tags: -

At the top of any content managed pages:  ```<dycon:page name="pageName"/>``` The pageName corresponds to a page created
in the <strong>dycon</strong> ui.

To include content managed text: ```<dycon:content name="contentName" default="lorem ipsum"/>```

To include content managed text using the body of the tag as the default content:

```
<dycon:content name="contentName" useBodyAsDefault="true">
        lorem ipsum <strong> this is bold </strong>
</dycon:content>
```


To include content managed images: ```<dycon:image name="imageName" default="defaultImageLocation"/>```



