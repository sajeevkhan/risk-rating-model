import { FuseNavigation } from '@fuse/types';

export const navigation: FuseNavigation[] = [
    {
        id: 'applications',
        title: 'Applications',
        translate: 'NAV.APPLICATIONS',
        type: 'group',
        children: [
            {
                id: 'inbox',
                title: 'Inbox',
                translate: 'NAV.INBOX',
                type: 'item',
                icon: 'email',
                url: '/inbox'
                /* badge    : {
                    title    : '25',
                    translate: 'NAV.SAMPLE.BADGE',
                    bg       : '#F44336',
                    fg       : '#FFFFFF'
                } */
            },
            {
                id: 'projects',
                title: 'Projects',
                translate: 'NAV.PROJECTS',
                type: 'item',
                icon: 'business',
                url: '/projects'
            },
            {
                id: 'reports',
                title: 'Reports',
                translate: 'NAV.REPORTS',
                type: 'collapsable',
                icon: 'view_list',
                children: [
                    {
                        id: 'changeHistory',
                        title: 'Change History',
                        translate: 'NAV.CHANGEHISTORY',
                        type: 'item',
                        icon: 'view_list',
                        url: '/changeDocuments'
                    },
                ]
            }

        ]
    }
];
