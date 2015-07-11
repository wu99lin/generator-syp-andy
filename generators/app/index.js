'use strict';
var util = require('util');
var path = require('path');
var yeoman = require('yeoman-generator');
var yosay = require('yosay');
var chalk = require('chalk');

var androidSdkVersionsChoices = [
  'API 11: Android 3.0 (Honeycomb)',
  'API 12: Android 3.1 (Honeycomb)',
  'API 13: Android 3.2 (Honeycomb)',
  'API 14: Android 4.0 (Ice Cream Sandwich)',
  'API 15: Android 4.0.3 (Ice Cream Sandwich)',
  'API 16: Android 4.1 (Jelly Bean)',
  'API 17: Android 4.2 (Jelly Bean)',
  'API 18: Android 4.3 (Jelly Bean)',
  'API 19: Android 4.4.2 (KitKat)'
].map(function(name, value) {
  return {name: name, value: value + 1};
});


var SypAndyGenerator = yeoman.generators.Base.extend({
  constructor: function () {
    yeoman.generators.Base.apply(this, arguments);

    this.pkg = require('../../package.json');
  },

  askFor: function () {
    var done = this.async();

    console.log('\nWelcome to the SypAndy Generator\n');
    // say hello Yo!
    this.log(yosay(
        'Yo Android Generator SypAndy\n'+ '中国项目的AndroidApp\n'+ '吴至林（善因平）整理\n'+ 'OK SypAndy! Generate this app!'
    ));

    var prompts = [
      {
        name: 'applicationName',
        message: 'Application name:',
        default: 'My Application'
      },
      {
        name: 'moduleName',
        message: 'Module name:',
        default: 'app'
      },
      {
        name: 'packageName',
        message: 'Package name:',
        default: 'com.application.app'
      },
      {
        name: 'minimumApiLevel',
        message: 'Minimum required SDK:',
        type: 'list',
        choices: androidSdkVersionsChoices,
        default: 13 // API 14: Android 4.0 (Ice Cream Sandwich)
      },
      {
        name: 'targetSdk',
        message: 'Target SDK:',
        type: 'list',
        choices: androidSdkVersionsChoices,
        default: 8 // API 19: Android 4.4.2 (KitKat)
      },
      {
        name: 'compileSdkVersion',
        message: 'Compile with:',
        type: 'list',
        choices: androidSdkVersionsChoices,
        default: 18 // API 19: Android 4.4.2 (KitKat)
      },
      {
        name: 'javaLanguageLevel',
        message: 'Java language level:',
        type: 'list',
        choices: [
          {name: '6.0 – @Override in interfaces', value: 'VERSION_1_6'},
          {name: '7.0 – Diamonds, ARM, multi-catch…', value: 'VERSION_1_7'}
        ],
        default: 1 // 7.0 – Let's use an advanced version of Java, plz!
      },
      {
        name: 'theme',
        message: 'Theme:',
        type: 'list',
        choices: [
          'None',
          'Holo Dark',
          'Holo Light',
          'Holo Light with Dark Action Bar'
          ].map(function(name, value) {
            return {name: name, value: value};
          }),
        default: 3 // Holo Light with Dark ActionBar
      }
    ];

    this.prompt(prompts, function (answers) {
      this.applicationName = answers.applicationName;
      this.moduleName = answers.moduleName;
      this.packageName = answers.packageName;
      this.packagePath = this.packageName.replace(/\./g, '/');
      this.minimumApiLevelVersion = answers.minimumApiLevel;
      this.targetSdkVersion = answers.targetSdk;
      this.compileSdkVersion = answers.compileSdkVersion;
      this.javaLanguageLevel = answers.javaLanguageLevel;
      this.theme = answers.theme;
      this.supportLibraries = answers.supportLibraries || {};

      done();
    }.bind(this));
  },

  git: function() {
    this.copy('gitignore', '.gitignore');
  },

  app: function() {
    // Generate basic dirs
    this.mkdir(this.moduleName);
    this.mkdir('libraries');
    this.mkdir(this.moduleName + '/libs');
    this.mkdir(this.moduleName + '/src');
    this._mkdirs(this.moduleName + '/src', ['main']);
    // Start with main folder
    this.template('_AndroidManifest.xml', this.moduleName + '/src/main/AndroidManifest.xml');
    this.mkdir(this.moduleName + '/src/main/java/' + this.packagePath);

    this.directory('_res', this.moduleName + '/src/main/res');
    this.copy('proguard-android.txt', this.moduleName + '/proguard-android.txt');
    this.copy('proguard-rules.pro', this.moduleName + '/proguard-rules.pro');
  },
  kjframe:function(){
    this.directory('libraries/KJFrame', 'libraries/KJFrame');
  },
  root: function() {
    this.template('_src/_AppConfig.java', this.moduleName + '/src/main/java/' + this.packagePath + '/AppConfig.java');
    this.template('_src/_AppContext.java', this.moduleName + '/src/main/java/' + this.packagePath + '/AppContext.java');
    this.template('_src/_CrashHandler.java', this.moduleName + '/src/main/java/' + this.packagePath + '/CrashHandler.java');
  },
  service: function() {
    this.template('_src/service/_CommonService.java', this.moduleName + '/src/main/java/' + this.packagePath + '/service/CommonService.java', this, {});
  },
  domain: function() {
    this.template('_src/domain/_SimpleBackPage.java', this.moduleName + '/src/main/java/' + this.packagePath + '/domain/SimpleBackPage.java', this, {});
  },
  ui: function() {
    this.template('_src/ui/_AppStart.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/AppStart.java');
    this.template('_src/ui/_Browser.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/Browser.java');
    this.template('_src/ui/_Main.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/Main.java');
    this.template('_src/ui/_SimpleBackActivity.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/SimpleBackActivity.java');
    this.template('_src/ui/_Splash.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/Splash.java');
    this.template('_src/ui/_TitleBarActivity.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/TitleBarActivity.java');

  },
  fragment: function() {
    this.template('_src/ui/fragment/_BlankFragment.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/fragment/BlankFragment.java');
    this.template('_src/ui/fragment/_BlogFragment.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/fragment/BlogFragment.java');
    this.template('_src/ui/fragment/_FindFragment.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/fragment/FindFragment.java');
    this.template('_src/ui/fragment/_MineFragment.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/fragment/MineFragment.java');
    this.template('_src/ui/fragment/_TitleBarFragment.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/fragment/TitleBarFragment.java');
    this.template('_src/ui/fragment/_MessageFragment.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/fragment/MessageFragment.java');

  },
  widget: function() {
    this.template('_src/ui/widget/_CollapsibleTextView.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/CollapsibleTextView.java');
    this.template('_src/ui/widget/_EmptyLayout.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/EmptyLayout.java');
    this.template('_src/ui/widget/_KJDragGridView.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/KJDragGridView.java');
    this.template('_src/ui/widget/_KJScrollView.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/KJScrollView.java');
    this.template('_src/ui/widget/_RecordButton.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/RecordButton.java');
    this.template('_src/ui/widget/_RecordButtonUtil.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/RecordButtonUtil.java');
    this.template('_src/ui/widget/_ShapeHolder.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/ShapeHolder.java');
   },
  listview: function() {
    this.template('_src/ui/widget/listview/_FooterLoadingLayout.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/listview/FooterLoadingLayout.java');
    this.template('_src/ui/widget/listview/_HeaderLoadingLayout.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/listview/HeaderLoadingLayout.java');
    this.template('_src/ui/widget/listview/_ILoadingLayout.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/listview/ILoadingLayout.java');
    this.template('_src/ui/widget/listview/_IPullToRefresh.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/listview/IPullToRefresh.java');
    this.template('_src/ui/widget/listview/_LoadingLayout.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/listview/LoadingLayout.java');
    this.template('_src/ui/widget/listview/_PullToRefreshBase.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/listview/PullToRefreshBase.java');
    this.template('_src/ui/widget/listview/_PullToRefreshList.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/listview/_PullToRefreshList.java');
  },
  dobmenu: function() {
    this.template('_src/ui/widget/dobmenu/_AnimationExecutor.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/dobmenu/AnimationExecutor.java');
    this.template('_src/ui/widget/dobmenu/_CurtainItem.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/dobmenu/CurtainItem.java');
    this.template('_src/ui/widget/dobmenu/_CurtainView.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/dobmenu/CurtainView.java');
    this.template('_src/ui/widget/dobmenu/_CurtainViewController.java', this.moduleName + '/src/main/java/' + this.packagePath + '/ui/widget/dobmenu/_CurtainViewController.java');
  },
  utils: function() {
    this.template('_src/utils/_KJAnimations.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/KJAnimations.java');
    this.template('_src/utils/_MailSenderInfo.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/MailSenderInfo.java');
    this.template('_src/utils/_MyAuthenticator.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/MyAuthenticator.java');
    this.template('_src/utils/_Parser.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/Parser.java');
    this.template('_src/utils/_PullTip.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/PullTip.java');
    this.template('_src/utils/_SimpleMailSender.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/SimpleMailSender.java');
    this.template('_src/utils/_TimeUtils.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/TimeUtils.java');
    this.template('_src/utils/_UIHelper.java', this.moduleName + '/src/main/java/' + this.packagePath + '/utils/UIHelper.java');
  },
  gradle: function() {
    this.directory('gradle', 'gradle');
   // this.directory('tasks', 'tasks');
    this.copy('gradle.properties', 'gradle.properties');
    this.copy('gradlew', 'gradlew');
    this.copy('gradlew.bat', 'gradlew.bat');
    this.template('config.gradle', 'config.gradle');
    this.template('_settings.gradle', 'settings.gradle');
    this.template('_build.root.gradle', 'build.gradle');
    this.template('_build.app.gradle', this.moduleName + '/build.gradle');
  },



  _mkdirs: function(path, dirs, append) {
    append = append ? '/' + append : '';
    dirs.forEach(function(entry) {
      this.mkdir(path + '/' + entry + append);
    }.bind(this));
  }

});

module.exports = SypAndyGenerator;
