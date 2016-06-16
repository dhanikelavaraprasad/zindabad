'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'storyService', 'userService', 'appConstants', '$location', function($scope, $rootScope, storyService, userService, appConstants, $location) {
	
	$scope.changeTab = function (currentTab) {
		currentTab === 'BACKLOG' ? getBackLogStories() : getStories();
		$scope.selectedTab = currentTab;
	};

  $scope.closeModal = closeModal;
  $scope.story = {};
  $scope.errorMessage = undefined;
	$scope.changeTab('CURRENT');
  $scope.selectedStoryList = [];
  $scope.storiesList = [];
  $scope.isAllSelected = false;

	$scope.showModal = function(id, story) {
    $scope.story = angular.copy(story) || {};
    var element = angular.element(id);
    element.modal('show');
  }

  $scope.addStory = function(story) {
    storyService.addStory(story)
      .success(function () {
        getBackLogStories();
        closeModal('#add');
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to process your request';
      });
  }

  function closeModal(id) {
    clearErrorMessages();
    var element = angular.element(id);
    element.modal('hide');
  }

	function getStories() {
    storyService.getStories()
      .success(function (dataStories) {
       $scope.stories = dataStories;
      })
      .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
      });
  }
    
    function getBackLogStories() {
      storyService.getBackLogStories()
        .success(function (backlogtories) {
         $scope.backlogtories = backlogtories;
        })
        .error(function (error) {
            $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    function clearErrorMessages() {
      $scope.errorMessage = undefined;
    }

    function getUser() {
      userService.getUser()
        .success(function (dataUser) {
          $scope.users = dataUser;
          
                
        })
        .error(function (error) {
          $scope.status = 'Unable to load customer data: ' + error.message;
        });
    }
  
    getUser();


  $scope.user =  appConstants.getItem('currentUser');
 
  
  $scope.isSelectAll = function(){

     if($scope.isAllSelected){
        
        $scope.selectedStoryList = angular.copy($scope.backlogtories);
      }
      else{
        $scope.selectedStoryList = [];
      }
    }

  $scope.isLabelChecked = function(story, isSelected, index){

    if (isSelected) {
      $scope.selectedStoryList.push(story);
    } else {
      $scope.selectedStoryList.splice(index, 1);
    }
  } 

  $scope.assignToSprint = function(){

    storyService.assignToSprint($scope.selectedStoryList, $scope.assignToId)
      .success(function () {
       getBackLogStories();
      })
      .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
      });
  }

  
	$scope.$apply();
		
	}];
});